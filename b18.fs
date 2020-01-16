in vec2 texCoordV;
out vec4 op;
void main(void) {
  vec2 uv = (gl_FragCoord.xy/ iResolution.xy);
  uv.y=1.0-uv.y*1;
  //uv.x = uv.x + 5.5*sin(0.015*iGlobalTime);
  //uv.y = uv.y + 2.5*cos(0.03*iGlobalTime);
  float data1_0=iDataArray1[0];
  float data1_1=iDataArray1[1];
  float data2_0=iDataArray2[0];
  float sclr=10000;//*iFloat1;
  uv=floor(uv * (sclr+iRandom*iFloat1 )) / ( sclr+iRandom*iFloat1 + data1_0);
  vec2 uv2=gl_FragCoord.xy*texCoordV/ iResolution.xy;

  vec4 iChannel1_texture=texture2D(iChannel1, uv);
  vec4 iChannel2_texture=texture2D(iChannel2, uv);
  vec4 iChannel3_texture=texture2D(iChannel3, uv);
  vec4 iChannel4_texture=texture2D(iChannel4, uv);
  vec4 iChannel5_texture=texture2D(iChannel5, uv);
  vec4 iChannel6_texture=texture2D(iChannel6, uv);
  vec4 iChannel7_texture=texture2D(iChannel7, uv);

  vec4 ich[6];
  ich[0]=iChannel1_texture;
  ich[1]=iChannel2_texture;
  ich[2]=iChannel3_texture;
  ich[3]=iChannel4_texture;
  ich[4]=iChannel5_texture;
  ich[5]=iChannel6_texture;

  int timefloor=min(int(floor( 6* (1+(sin(iGlobalTime*10.41))))), 5);

  vec4 pf1=texture2D(iPreviousFrame, uv);
  vec4 text=texture2D(iText, uv);
  vec4 ccc=vec4(cos(iGlobalTime*10.41)+data2_0, data1_0, sin(iGlobalTime*3.14+data1_1), 1);
  vec4 ppp=mix(iChannel2_texture, ccc, 0.5);
  float fade_size=2;
  float p1= mix(fade_size, 0.0-fade_size, uv.x-0.125*iFloat1/100);
  vec4 mixxx =mix(iChannel7_texture, iChannel6_texture, smoothstep(1.0, 0.0, p1));
  vec4 mx2=mix(mixxx, iChannel6_texture, -1);
  op =iChannel7_texture; //mx2;//mixxx;// ich[timefloor];//mixxx;//mix(text, ppp, cos(iGlobalTime*1.41)+data2_0);//ppp;//text;//iChannel1_texture;//iChannel1_texture;
}
