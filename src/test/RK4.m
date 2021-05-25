%RUNGE KUTTA 4TH ORDER TESTING

GC = 6.67408e-11;%value of gravitational constant
MS = 1.988500e30; % Mass of central body

%initial co-ordinates of spaceship when it is launched
xi = [-1.4718861838613153E11; -2.8615219147677864E10 ;8174296.311571818];% NASA HORIZONS DEV
yi = [27978.003182957942; -62341.39349461967 ;-651.590970913659];

s = 365*24;%iteration
ss = 60*60; % first step size

yf = zeros(6, s+1);%creates a matrix of 6 rows and s+1 columns(not sure about s+1, thanks youtube)
yf(:, 1) = [xi;yi];%filling up every element in the array

start = zeros(3,1);
for i = 1:s
     yf(:,i+1) = yf(:,i) + step(GC,start, yf(:,i),MS,ss);
end
clc;
plot3(yf(1, :), yf(2, :), yf(3, :), 'BLACK');
title("RUNGE KUTTA TEST");

%Runge-Kutta
function [slope] = step(GC,start,yf,MS, ss)
    y1 = ss * derivCalc(GC, start, yf, MS);
    yn1 = yf + 1/2*y1;
    y2 = ss * derivCalc(GC,start, yn1, MS);
    yn2 = yf + 1/2*y2;
    y3 = ss * derivCalc(GC, start, yn2, MS);
    yn3 = yf + y3;
    y4 = ss * derivCalc(GC,start,yn3,MS);
    %When the four slopes are averaged, the slope of the
    %midpoint has greater weight
    slope =(y1+2*y2+2*y3+y4)*1/6;
end

%EULER PART, COMPARISONS
function [slope] = derivCalc(GC, start, yf, MS)
    slope = zeros(6, 1);%creates a matrix of 6 rows and s+1 columns
    slope(1:3) = yf(4:6);
    s = start - yf(1:3);
    dis = norm(s)^3;
    slope(4:6) = GC * MS * s/dis;
end
