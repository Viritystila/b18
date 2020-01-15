//#version 460 core
#define PI 3.14159


layout(location = 0) in vec4 vertexPosition_modelspace;
layout(location = 1) in vec3 colors_modelspace;

float ux = floor((gl_VertexID*1.0) / 8.0) + mod((gl_VertexID*1.0), 2.2);
float vy = mod(floor((gl_VertexID*1.0) / 2.0) + floor ((gl_VertexID*1.0) /3.0), 1.0*iFloat1);
float angle = ux /20.0 * radians(360.0) *3.0;
float radius = vy + 1.0+2*20;
float x = radius * cos(angle);
float y = radius * sin(angle);
vec2 xy = vec2(x,y);


out vec2 texCoordV;
void main(void) {
  float time = iGlobalTime + 20.0;
  float v = gl_VertexID;
  float vertex = mod(v, 6.);
  v = (v-vertex)/6.;
  float a1 = mod(v, 32.);
  v = (v-a1)/32.;
  float a2 = v;

  float a1n = (a1+.5)/32.*2.*PI;
  float a2n = (a2+.5)/32.*2.*PI;

  a1 += mod(vertex,2.);
  a2 +=  vertex==2.||vertex>=4.?1.:0.;

  a1 = a1/32.*2.*PI;
  a2 = a2/32.*2.*PI;
  float snd = 0.0;

  vec3 pos = vec3(cos(a1)*cos(a2),sin(a2),sin(a1)*cos(a2))  * mix(0.4, 3.2, pow(snd, 6.));
  vec3 norm = vec3(cos(a1n)*cos(a2n),sin(a2n),sin(a1n)*cos(a2n));
  norm = vec3(cos(a1)*cos(a2),sin(a2),sin(a1)*cos(a2));

pos.xz *= mat2(cos(time),sin(time),-sin(time),cos(time));
pos.yz *= mat2(cos(time),sin(time),-sin(time),cos(time));
  norm.xz *= mat2(cos(time),sin(time),-sin(time),cos(time));
  norm.yz *= mat2(cos(time),sin(time),-sin(time),cos(time));
  vec4 sp=vec4(pos.x*iResolution.y/iResolution.x,pos.y, pos.z*.5+.5, 1);
texCoordV=xy;

gl_Position = vertexPosition_modelspace;//*vec4(xy*0.1, 0.1, 1);
}
