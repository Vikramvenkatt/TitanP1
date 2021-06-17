package landing;


    /**
     * MAIN IDEA:FEEDBACK IS FED TO THE CONTROLLER TO COMPARE TO THE DESIRED VALUE, AND COMPUTES ERRORS-error term
     * WE want ZERO ERROR FROM PID OVER TIME!
     * "The PID (proportional–integral–derivative) controller maintains the output to the process plant such that
     * there is zero error e(t) between process variable y(t) and set point u(t)
     * or desired output by a closed loop or feedback loop mechanism."
     * practice stuff, setting general conditions for closed loop physics equations to implement
     * what is all this ki bs?
     * https://robotics.stackexchange.com/questions/9786/how-do-the-pid-parameters-kp-ki-and-kd-affect-the-heading-of-a-differential
     * http://brettbeauregard.com/blog/2011/04/improving-the-beginner%E2%80%99s-pid-reset-windup/
     */
    public class PIDtest {
        private double Kp;
        private double Ki;
        private double Kd;
        private double targetPosition;//should be 0,0(x,y)
        private double targetVelocity;//should also be 0
        private double errorInPosition;
        private double errorInVelocity;
        private double oldError;
        private double totalErrorOfPos; //HORIZONTAL OR VERTICAL? PROBABLY HORIZONTAL
        private double tolerance;
        private double outMin = -Double.MAX_VALUE;//UPPER BOUND OF ALLOWED ERROR
        private double outMax = Double.MAX_VALUE;//LOWER BOUND

        /**
         * create constructors for k's
         * @param Kp
         * @param Ki
         * @param Kd
         * @param target_pos
         * @param target_vel
         * @param tolerance
         */
        public PIDtest(double Kp, double Ki, double Kd, double target_pos, double target_vel, double tolerance) {
            this.Kp = Kp;
            this.Ki = Ki;
            this.Kd = Kd;
            setTargetPosition(target_pos);
            setTargetVelocity(target_vel);
            setTolerance(tolerance);
        }

        public PIDtest(double Kp, double Ki, double Kd) {
            this(Kp, Ki, Kd, 0, 0, 0);
        }

        public void setTolerance(double tolerance) {
            this.tolerance = tolerance;
        }

        public void setTargetPosition(double target_pos) {
            this.targetPosition = target_pos;
        }

        public void setTargetVelocity(double target_vel) {
            this.targetVelocity = target_vel;
        }

        public boolean onTarget() {
            return  Math.abs(errorInPosition) < tolerance;
        }

        public void setKd(double kd) {
            Kd = kd;
        }

        public void setKi(double ki) {
            Ki = ki;
        }

        public void setKp(double kp) {
            Kp = kp;
        }

        public void BoundedLimits(double outMin, double outMax) {
            if (outMin > outMax) {
                throw new IllegalArgumentException("Lower Bound cannot be higher than Upper Bound");
            }
            this.outMin = outMin;
            this.outMax = outMax;
            if(totalErrorOfPos > outMax) {
                totalErrorOfPos = outMax;
            }
            else if(totalErrorOfPos < outMin) totalErrorOfPos = outMin;
        }
        //METHOD WHEN THERE IS ONLY POSITION INPUT I THINK
        public double calculateOutput(double input, double dt) {
            errorInPosition = input - targetPosition;

            // For kp
            double kpError = Kp * errorInPosition;

            // For D gain
            double kdD = (errorInPosition - oldError)/dt;
            double kdError = Kd * kdD;
            oldError = errorInPosition;

            // for I gain with anti windup
            totalErrorOfPos += errorInPosition;
            if(totalErrorOfPos > outMax) {
                totalErrorOfPos = outMax;}
            else if(totalErrorOfPos < outMin) {
                totalErrorOfPos = outMin;}
            double kiIntegrator = totalErrorOfPos *dt;
            double kiError = Ki * kiIntegrator;

            double output = kpError + kdError + kiError;
            if(output > outMax) {output = outMax;}
            if(output < outMin) {output = outMin;}


            return output;
        }
        //WHEN THERE IS BOTH POS AND VELOCITY
        public double calculateOutput(double pos_input, double vel_input, double dt) {
            errorInPosition = pos_input - targetPosition;//initial conditions when probe enters/leaves orbit
            errorInVelocity = vel_input - targetVelocity;//same

            // For kp gain
            double pError = Kp * errorInVelocity;
            // For kd gain
            double dError = Kd * errorInVelocity;


            totalErrorOfPos += errorInPosition;
            if(totalErrorOfPos > outMax) {
                totalErrorOfPos = outMax;}
            else if(totalErrorOfPos < outMin) {
                totalErrorOfPos = outMin;}
            double kpIntegrator = totalErrorOfPos *dt;
            double iError = Ki * kpIntegrator;

            double output = pError + dError + iError;
            if(output > outMax) {output = outMax;}
            if(output < outMin) {output = outMin;}

            return output;
        }

        /**
         * resetting total error to 0
         */
        public void resetTotalError(){
            totalErrorOfPos = 0;
        }

        public void setTotalError_pos(double totalError_pos) {
            this.totalErrorOfPos = totalError_pos;
        }

        public void reduceTotalError(double percentage){
            totalErrorOfPos = totalErrorOfPos *percentage;
        }
    }

