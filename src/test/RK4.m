close all; clear; clc;
%RUNGE KUTTA 4TH ORDER TESTING
G = 6.674e-11;%value of gravitational constant
mC = 1.989e30; % Mass of central body

origin = zeros(3,1);

%initial co-ordinates of spaceship when it is launched
x0 = [-1.4718861838613153E11, -2.8615219147677864E10 ,8174296.311571818];
y0 = [27978.003182957942, -62341.39349461967 ,-651.590970913659];

tf = 365 * 24 * 60 * 60;  %time calculation-NUMBER OF DAYS INTO DURATION * MINUTES*SECONDS
n = 365 * 24;
h = tf/n; % first step size calculation

ts = linspace(0, tf, n+1);%for generating the 100 or so linearly spaced vectors
ys = zeros(6, n+1);%initializing the array of zeros
ys(:, 1) = [x0;y0];%filling up every element in the array

ys2 = zeros(6, n+1);%creates a matrix of 6 rows and n+1 columns(not sure about n+1, thanks youtube)
ys2(:, 1) = [x0;y0];

for i = 1:n
     ys(:, i+1) = ys(:, i) + deriv(ys(:, i), G, mC, origin) * h;%taken from my understanding of lecture slides and Haoran's rk solver
     ys2(:, i+1) = ys2(:, i) + rk4(ys2(:, i), G, mC, origin, h);
end

figure;

plot3(ys(1, :), ys(2, :), ys(3, :), 'r');
title("Runge Kutta Testing");
figure;
plot3(ys2(1, :), ys2(2, :), ys2(3, :), 'b');
title("rk4");

 
% doty [ds, dv]
% this is equal to euler method
function [doty] = deriv(y, G, mC, origin)
    doty = zeros(6, 1);
    doty(1:3) = y(4:6);
    s = origin - y(1:3);
    r3 = norm(s)^3;
    doty(4:6) = s * G * mC / r3;
end


function [doty] = rk4(y,G,mC,origin, h)
    k1 = h * deriv(y, G, mC, origin);
    yp = y + 1/2*k1;
    k2 = h * deriv(yp, G, mC, origin);
    yp = y + 1/2*k2;
    k3 = h * deriv(yp, G, mC, origin);
    yp = y + k3;
    k4 = h * deriv(yp,G,mC,origin);
    doty =1/6*(k1+2*(k2+k3)+k4);
end