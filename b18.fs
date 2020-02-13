in vec2 texCoordV;
out vec4 op;

vec2 distortUV(vec2 uv, vec2 nUV, sampler2D nstex ,  float ip1)
{
  vec2 uv_orig=uv;
  float intensity = 0.1;
  float scale = 0.05+ip1;
  float speed = 1.06;


    nUV.x += ip1*speed;
    nUV.y += ip1*speed ;
    vec2 noise= texture(nstex, nUV*scale).xy;

    uv += ((-1.0+noise*2));// * intensity;

    return mix(uv, uv_orig, 0);;
}

vec2 noiseUV(vec2 uv, float mod1, float mod2){
  vec2 block =floor(gl_FragCoord.xy/vec2(16*1.1*mod2));
  vec2 uv_noise = block / vec2(64);
  uv_noise +=floor(vec2(mod1*10.3) * vec2(12345.0, 3543.0))/vec2(sqrt(mod1));
  return uv_noise;

}

vec4 glitch(vec2 uv_noise, vec2 uv,  vec4 v1In, vec4 v2In, float mod1, sampler2D tex1, sampler2D tex2){
  //https://www.shadertoy.com/view/Md2GDw
  //vec2 block = floor(fragCoord.xy / vec2(16));
  //vec2 uv_noise = block / vec2(64);
  uv_noise += floor(vec2(iRandom, iRandom*mod1) * vec2(1234.0, 3543.0)) / vec2(64);
  float block_thres =pow(fract(mod1+1234.0456), 2.0)*0.3;
  float line_thres =pow(fract(mod1+ 2229.04), 3.0)* 0.7;

  vec2 uv_r =uv, uv_g=uv, uv_b=uv;


  if (v1In.r< block_thres ||
      v2In.g <line_thres){
    vec2 dist = (fract(uv_noise)-0.5)*0.3;
    uv_r +=dist*0.1;
    uv_g +=dist*0.2;
    uv_b +=dist*0.125;
      }



  vec4 glitchText=v1In;
  glitchText.r = texture(tex2, uv_r).r;
  glitchText.g = texture(tex2, uv_g).g;
  glitchText.b = texture(tex2, uv_b).b;
	// loose luma for some blocks
	if (texture2D(tex1, uv_noise).g > block_thres)
		glitchText.rgb = v1In.ggg;

        	// discolor block lines
	if (texture2D(tex2, vec2(uv_noise.y, 0.0)).b * 2.5 <  line_thres)
          glitchText.rgb = vec3(0.0, dot(glitchText.rgb, vec3(1.0)), 1.10);


	// interleave lines in some blocks
	if (texture2D(tex1, uv_noise).g * 0.05  > block_thres ||
		texture(tex1, vec2(uv_noise.x, 0.0)).r * 2.5 > line_thres) {
		float line = fract(gl_FragCoord.x / 3.0);
		vec3 mask = vec3(1.0, 1.0, 0.0);
		if (line > 0.333){
                  //discard;
                  mask = vec3(3.0, 1.0, 0.0);
                }
		if (line > 0.666)
			mask = vec3(0.0, 1.0, 1.0);

		glitchText.xyz *= mask;
	}

        return glitchText;

}


vec4 colorRemoval(vec4 fg, vec4 bg, float th, float mod1, float r, float g, float b){
  vec3 color_diff = fg.rgb - vec3(r, g, b);
  float squared_distance = dot(color_diff, color_diff);
  if (squared_distance < (mod1*th))
   {
     fg = bg;
   }

  return fg;

}

vec4 waveColors(vec4 v1In, vec2 uv, float mod1, float ns, float w, float lines){
  const float tau = 6.28318530717958647692;
  vec3 wave = vec3(0.0);
  //float width = v0.x*((iDataArray[0]*iDataArray[0]*iDataArray[0]*iDataArray[0])/100000000);
  float n=10;
  float width=w*mod1;
  for (int i=0; i < lines; i++){
    n=1; //sin(iDataArray[0]);
    float sound =v1In.x;
    float xymix=mix(uv.y, uv.x, 0);

    float a = 0.1*float(i)*tau/float(n);
    vec3 phase = smoothstep(-1.0,1.5, vec3(cos(a), cos(a-tau/3.0), cos(a-tau*2.0/3.0)));
    wave += phase * smoothstep(width, 0.0, abs(xymix - ((sound*0.5)+0.2)));

    //This shift of uv.x means our index into the sound data also
    //moves along, examining a different part of the audio wave.
    uv.x += 0.4/float(n);
    uv.y -= 0.05*mod1;
  }
  wave *= 10/float(10); // * iDataArray[0];
  vec4 cf8=vec4(wave, 1);
  return cf8;
}

vec4 chromaKey(vec4 fg, vec4 bg){
  float maxrb = max( fg.r, fg.g);
  float k = clamp( (fg.b-maxrb)*90, 0.0, 1.0);

  float dg = fg.b;
  fg.b = min( fg.b, maxrb);//iDataArray[0]);
    fg += dg - fg.b;

    vec4 cf6=mix(fg, bg, k); //-sin(iDataArray[0]));
    return cf6;
}

vec4 kaleoidscope(vec2 n, float mod1, float numbi, float numbc,  sampler2D tex0)
{
  vec2  u = n;
        vec2 p = -1. + 2. * u;
	float t = mod1,
          a = atan(p.y, p.x) ,
          r = length(p)*1 ,
          c = numbc * cos(t + 7. * a);
        float numb= numbi;
	vec4 o = vec4(
        texture(
            tex0,
            vec2(numb * a / 5.14, -t + sin(numb  * r + t) + c) * 0.5)) * (1.75 + 0.75 * (sin(t + numb * r) + c));
        return o;
}
void main(void) {
  vec2 uv = (gl_FragCoord.xy/ iResolution.xy);
  vec2 uv2 = uv;
  uv.y=1.0-uv.y*1;
  float sclr=1000*iFloat2;//*iFloat1;
  //uv=floor(uv * (sclr+iFloat1/10 )) / ( sclr+iFloat1 );
  vec2 uv_noise=noiseUV(uv, 1, 0.1/(1+iFloat3));
  vec2 dsUV=distortUV(uv, uv, iChannel2, 0.2);

  vec4 txt=texture2D(iText, uv);

  vec4 ic1=texture2D(iChannel1, uv2);
  ic1.rgb=ic1.bgr;
  vec4 ic2=texture2D(iChannel2, uv);
  vec4 ic3=texture2D(iChannel3, uv);
  vec4 ic4=texture2D(iChannel4, uv);
  vec4 ic5=texture2D(iChannel5, uv);
  vec4 ic6=texture2D(iChannel6, uv);
  vec4 ic7=texture2D(iChannel7, uv);
  vec4 ic8=texture2D(iChannel8, uv);
  vec4 ic9=texture2D(iChannel9, uv);
  vec4 ic10=texture2D(iChannel10, uv);
  vec4 ic11=texture2D(iChannel11, uv);
  vec4 ic12=texture2D(iChannel12, uv);
  vec4 ic13=texture2D(iChannel13, uv);

  vec4 ic2n=texture2D(iChannel2, uv_noise);
  vec4 ic3n=texture2D(iChannel3, uv_noise);
  vec4 ic4n=texture2D(iChannel4, uv_noise);
  vec4 ic5n=texture2D(iChannel5, uv_noise);
  vec4 ic6n=texture2D(iChannel6, uv_noise);
  vec4 ic7n=texture2D(iChannel7, uv_noise);
  vec4 ic8n=texture2D(iChannel8, uv_noise);
  vec4 ic9n=texture2D(iChannel9, uv_noise);
  vec4 ic10n=texture2D(iChannel10, uv_noise);
  vec4 ic11n=texture2D(iChannel11, uv_noise);
  vec4 ic12n=texture2D(iChannel12, uv_noise);
  vec4 ic13n=texture2D(iChannel13, uv_noise);

  vec4 ic2d=texture2D(iChannel2, dsUV);
  vec4 ic3d=texture2D(iChannel3, dsUV);
  vec4 ic4d=texture2D(iChannel4, dsUV);
  vec4 ic5d=texture2D(iChannel5, dsUV);
  vec4 ic6d=texture2D(iChannel6, dsUV);
  vec4 ic7d=texture2D(iChannel7, dsUV);
  vec4 ic8d=texture2D(iChannel8, dsUV);
  vec4 ic9d=texture2D(iChannel9, dsUV);
  vec4 ic10d=texture2D(iChannel10, dsUV);
  vec4 ic11d=texture2D(iChannel11, dsUV);
  vec4 ic12d=texture2D(iChannel12, dsUV);
  vec4 ic13d=texture2D(iChannel13, dsUV);


  vec4 ic2g=glitch(uv_noise, uv, ic2, ic3, iFloat3, iChannel3, iChannel2);
  vec4 ic3g=glitch(dsUV, uv, ic3, ic3, 1, iChannel3, iChannel2);
  vec4 ic4g=texture2D(iChannel4, dsUV);
  vec4 ic5g=texture2D(iChannel5, dsUV);
  vec4 ic6g=texture2D(iChannel6, dsUV);
  vec4 ic7g=texture2D(iChannel7, dsUV);
  vec4 ic8g=glitch(dsUV, uv, ic8, ic8, 1+iFloat4, iChannel3, iChannel8);


  vec2 pfuv=uv;
  pfuv.y=pfuv.x-pfuv.y;
  vec4 iText_texture=texture2D(iText, uv);
  vec4 pf=texture2D(iPreviousFrame, uv);
  vec4 pf2=texture2D(iPreviousFrame, pfuv);
  vec4 pfn=texture2D(iPreviousFrame, uv_noise);
  vec4 pfg=texture2D(iPreviousFrame, dsUV);

  int set_switch=int(floor(iFloat15));

    switch(set_switch){
    case 0:
      float fade_size=2;
      float p1= mix(fade_size, 0.0-fade_size, uv.x-5*iFloat1*iFloat1);
      vec4 o1=mix(ic1, ic3d+txt, smoothstep(1, 0, p1));
      vec4 o1b=colorRemoval(ic3, o1, 1, 0.2, 0, 0, 0);
      op=o1b;
      break;
    case 1:
      fade_size=2;
      p1= mix(fade_size, 0.0-fade_size, uv.x-0.05*iFloat3*5);
      o1=mix(ic1, ic2, smoothstep(1, 0, p1));
      o1b=colorRemoval(ic3, o1, 1, 0.2, 0, 0, 1);
      //gb2 tulee mukaan
      vec4 o2= colorRemoval(ic2, ic2g, 1, 1, 0.6, 0.93, 1);
      op=o2;
      break;
    case 2:
      vec4 o3in=mix(ic3d, ic8g, 1);
      //vec4 o3in=mix(ipvid, ic3d, iFloat4);
      vec4 o3 = mix(ic1, o3in, 1);  //4 is good, d or g too
      //vec4 o3b = mix(ic4, o3in, 4+iFloat3);  //4 is good, d or g too
      o3=colorRemoval(o3, ic1, 10, 0.05, 0.94, 0.898, 0.43);
      //vec4 o3b=colorRemoval(ic8, ic3g, 1, 0.2, 0.5, 0.8, 0.3);
      //o3=mix(o3b, o3, iFloat4);
      op=o3;
      break;
    case 3:
      vec4 vmss=mix(ic5, pf, 0.75+iFloat5/1); //60000
      o3=colorRemoval(vmss, ic1, 1, 0.3, 0.0, 0.0, 0.93);
      op=o3;
      break;
    case 4:
      vec4 o4in=mix(ic3n, ic3g, 1);
      //vec4 o3in=mix(ipvid, ic3d, iFloat4);
      vec4 o4 = mix(ic4, o4in, 4+iFloat6);  //4 is good, d or g too
      //vec4 o3b = mix(ic4, o3in, 4+iFloat3);  //4 is good, d or g too
      o4=colorRemoval(o4, ic3, 1, 0.92, 0, 0, 0);
      //vec4 o3b=colorRemoval(ic8, ic3g, 1, 0.2, 0.5, 0.8, 0.3);
      //o3=mix(o3b, o3, iFloat4);
      op=o4;
      break;
    case 5:
      vec4 o5pf = mix(ic1, pfg, 0.993595);
      vec4 o5=colorRemoval(o5pf, ic10, 1, 0.5, 0, 0, 0);
      o5=mix(o5, ic10, 10);
      op=o5;
      break;
    case 6:
      vec4 o6=colorRemoval(ic12+ic2*ic7, ic1, 1, 0.052*iFloat7*30, 0, 0, 0);
      o6=mix(o6, pf, iFloat7*85 );
      op=o6;
      break;
    case 7:
      op=waveColors(ic6, uv, 1, 0.1, 0.1, 1+int(floor(iFloat8*iFloat8*iFloat8*300000)));//ic6;
      op=mix(op, pf2, 0.679999+0.25+iFloat8);
      break;

    }
    //op=ic8g;
    //op =o1b;//iChannel6_texture;//mixxx;// ich[timefloor];//mixxx;//mix(text, ppp, cos(iGlobalTime*1.41)+data2_0);//ppp;//text;//iChannel1_texture;//iChannel1_texture;
}
