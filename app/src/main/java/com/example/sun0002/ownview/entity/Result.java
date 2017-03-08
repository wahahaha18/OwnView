package com.example.sun0002.ownview.entity;

import java.util.List;

/**
 *
 * Created by sun0002 on 2017/3/8.
 */

public class Result {

    /**
     * a : 0
     * b : [{"ItemType":"GlassRoom","TextLabel":"test","StrBorderStyle":1,"X":358.25,"Y":252.89,"W":413,"H":186},{"StandardDirection":0,"RollingBed":{"ChartLabel":"RB name","StandardDirection":0,"PointName":"RB point","RotateAngle":0,"PointValue":null,"PointValueCol":[{"Key":"IsAuto","Value":"0","Value_bool":false},{"Key":"IsManual","Value":"1","Value_bool":false},{"Key":"IsPrepairing","Value":"2","Value_bool":false},{"Key":"WarningC","Value":"3","Value_bool":false},{"Key":"WarningB","Value":"4","Value_bool":false},{"Key":"CommingIn","Value":"9","Value_bool":false},{"Key":"CommingOut","Value":"20","Value_bool":false},{"Key":"EntryClosed","Value":"10","Value_bool":false},{"Key":"FWDRotatingHighSpeed","Value":"12","Value_bool":false},{"Key":"REVRotatingHighSpeed","Value":"13","Value_bool":false},{"Key":"FWDRotatingLowSpeed","Value":"14","Value_bool":false},{"Key":"REVRotatingLowSpeed","Value":"15","Value_bool":false},{"Key":"Exists","Value":"16","Value_bool":false},{"Key":"ExitClosed","Value":"18","Value_bool":false},{"Key":"IsVoidMode","Value":"31","Value_bool":false}],"PointValueOptions":[],"ItemType":"RollingBed","X":0,"Y":0,"W":0,"H":0},"ChartLabel":"sample name","PointName":"sample point","StrBins":"10,20,30","PointValueCol":[{"Key":"IsAuto","Value":"0","Value_bool":false},{"Key":"IsManual","Value":"1","Value_bool":false},{"Key":"IsPrepairing","Value":"2","Value_bool":false},{"Key":"WarningC","Value":"3","Value_bool":false},{"Key":"WarningB","Value":"4","Value_bool":false},{"Key":"FWDRotatingHighSpeed","Value":"12","Value_bool":false},{"Key":"REVRotatingHighSpeed","Value":"13","Value_bool":false},{"Key":"FWDRotatingLowSpeed","Value":"14","Value_bool":false},{"Key":"REVRotatingLowSpeed","Value":"15","Value_bool":false},{"Key":"Position1","Value":"16","Value_bool":false},{"Key":"Position2","Value":"17","Value_bool":false},{"Key":"Position3","Value":"18","Value_bool":false},{"Key":"Position4","Value":"19","Value_bool":false},{"Key":"Position5","Value":"20","Value_bool":false},{"Key":"Position6","Value":"21","Value_bool":false},{"Key":"Position7","Value":"22","Value_bool":false},{"Key":"Position8","Value":"23","Value_bool":false}],"PointValueOptions":[],"ItemType":"STLBed","X":86.25,"Y":271.89,"W":126,"H":432},{"StandardDirection":0,"ChartLabel":"sample name","PointName":"fdgfdfdgfd","RotateAngle":0,"PointValueCol":[{"Key":"IsAuto","Value":"0","Value_bool":false},{"Key":"IsManual","Value":"1","Value_bool":false},{"Key":"IsPrepairing","Value":"2","Value_bool":false},{"Key":"WarningC","Value":"3","Value_bool":false},{"Key":"WarningB","Value":"4","Value_bool":false},{"Key":"CommingIn","Value":"9","Value_bool":false},{"Key":"CommingOut","Value":"20","Value_bool":false},{"Key":"EntryClosed","Value":"10","Value_bool":false},{"Key":"FWDRotatingHighSpeed","Value":"12","Value_bool":false},{"Key":"REVRotatingHighSpeed","Value":"13","Value_bool":false},{"Key":"FWDRotatingLowSpeed","Value":"14","Value_bool":false},{"Key":"REVRotatingLowSpeed","Value":"15","Value_bool":false},{"Key":"Exists","Value":"16","Value_bool":false},{"Key":"ExitClosed","Value":"18","Value_bool":false},{"Key":"IsVoidMode","Value":"31","Value_bool":false}],"PointValueOptions":[],"ItemType":"RollingBed","X":458.25,"Y":534.89,"W":120,"H":60},{"StandardDirection":0,"ChartLabel":"sample name","PointName":"sample point","RotateAngle":0,"PointValueCol":[{"Key":"IsAuto","Value":"0","Value_bool":false},{"Key":"IsManual","Value":"1","Value_bool":false},{"Key":"IsPrepairing","Value":"2","Value_bool":false},{"Key":"WarningC","Value":"3","Value_bool":false},{"Key":"WarningB","Value":"4","Value_bool":false},{"Key":"CommingIn","Value":"9","Value_bool":false},{"Key":"CommingOut","Value":"20","Value_bool":false},{"Key":"EntryClosed","Value":"10","Value_bool":false},{"Key":"FWDRotatingHighSpeed","Value":"12","Value_bool":false},{"Key":"REVRotatingHighSpeed","Value":"13","Value_bool":false},{"Key":"FWDRotatingLowSpeed","Value":"14","Value_bool":false},{"Key":"REVRotatingLowSpeed","Value":"15","Value_bool":false},{"Key":"Exists","Value":"16","Value_bool":false},{"Key":"ExitClosed","Value":"18","Value_bool":false},{"Key":"IsVoidMode","Value":"31","Value_bool":false}],"PointValueOptions":[],"ItemType":"RollingBed","X":704.25,"Y":126.89,"W":120,"H":60},{"ChartLabel":"工作站","PointName":"sample point","PointValueCol":[{"Key":"Red","Value":"1","Value_bool":false},{"Key":"Green","Value":"2","Value_bool":false},{"Key":"Yellow","Value":"0","Value_bool":false}],"PointValueOptions":[],"ItemType":"Robot","X":66.25,"Y":145.89,"W":60,"H":80},{"ItemType":"GlassRoom","TextLabel":"Sample Label2","StrBorderStyle":0,"X":358.25,"Y":20.89,"W":200,"H":200},{"ChartLabel":"sample text","PointName":"sample point","PointValueCol":[{"Key":"Red","Value":"1","Value_bool":false},{"Key":"Green","Value":"2","Value_bool":false},{"Key":"Yellow","Value":"0","Value_bool":false}],"PointValueOptions":[],"ItemType":"Robot","X":316.25,"Y":487.89,"W":60,"H":80}]
     */

    private String a;
    private List<BBean> b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public List<BBean> getB() {
        return b;
    }

    public void setB(List<BBean> b) {
        this.b = b;
    }

    public static class BBean {
        /**
         * ItemType : GlassRoom
         * TextLabel : test
         * StrBorderStyle : 1
         * X : 358.25
         * Y : 252.89
         * W : 413.0
         * H : 186.0
         * StandardDirection : 0
         * RollingBed : {"ChartLabel":"RB name","StandardDirection":0,"PointName":"RB point","RotateAngle":0,"PointValue":null,"PointValueCol":[{"Key":"IsAuto","Value":"0","Value_bool":false},{"Key":"IsManual","Value":"1","Value_bool":false},{"Key":"IsPrepairing","Value":"2","Value_bool":false},{"Key":"WarningC","Value":"3","Value_bool":false},{"Key":"WarningB","Value":"4","Value_bool":false},{"Key":"CommingIn","Value":"9","Value_bool":false},{"Key":"CommingOut","Value":"20","Value_bool":false},{"Key":"EntryClosed","Value":"10","Value_bool":false},{"Key":"FWDRotatingHighSpeed","Value":"12","Value_bool":false},{"Key":"REVRotatingHighSpeed","Value":"13","Value_bool":false},{"Key":"FWDRotatingLowSpeed","Value":"14","Value_bool":false},{"Key":"REVRotatingLowSpeed","Value":"15","Value_bool":false},{"Key":"Exists","Value":"16","Value_bool":false},{"Key":"ExitClosed","Value":"18","Value_bool":false},{"Key":"IsVoidMode","Value":"31","Value_bool":false}],"PointValueOptions":[],"ItemType":"RollingBed","X":0,"Y":0,"W":0,"H":0}
         * ChartLabel : sample name
         * PointName : sample point
         * StrBins : 10,20,30
         * PointValueCol : [{"Key":"IsAuto","Value":"0","Value_bool":false},{"Key":"IsManual","Value":"1","Value_bool":false},{"Key":"IsPrepairing","Value":"2","Value_bool":false},{"Key":"WarningC","Value":"3","Value_bool":false},{"Key":"WarningB","Value":"4","Value_bool":false},{"Key":"FWDRotatingHighSpeed","Value":"12","Value_bool":false},{"Key":"REVRotatingHighSpeed","Value":"13","Value_bool":false},{"Key":"FWDRotatingLowSpeed","Value":"14","Value_bool":false},{"Key":"REVRotatingLowSpeed","Value":"15","Value_bool":false},{"Key":"Position1","Value":"16","Value_bool":false},{"Key":"Position2","Value":"17","Value_bool":false},{"Key":"Position3","Value":"18","Value_bool":false},{"Key":"Position4","Value":"19","Value_bool":false},{"Key":"Position5","Value":"20","Value_bool":false},{"Key":"Position6","Value":"21","Value_bool":false},{"Key":"Position7","Value":"22","Value_bool":false},{"Key":"Position8","Value":"23","Value_bool":false}]
         * PointValueOptions : []
         * RotateAngle : 0
         */

        private String ItemType;
        private String TextLabel;
        private int StrBorderStyle;
        private double X;
        private double Y;
        private double W;
        private double H;
        private int StandardDirection;
        private RollingBedBean RollingBed;
        private String ChartLabel;
        private String PointName;
        private String StrBins;
        private int RotateAngle;
        private List<PointValueColBeanX> PointValueCol;
        private List<?> PointValueOptions;

        public String getItemType() {
            return ItemType;
        }

        public void setItemType(String ItemType) {
            this.ItemType = ItemType;
        }

        public String getTextLabel() {
            return TextLabel;
        }

        public void setTextLabel(String TextLabel) {
            this.TextLabel = TextLabel;
        }

        public int getStrBorderStyle() {
            return StrBorderStyle;
        }

        public void setStrBorderStyle(int StrBorderStyle) {
            this.StrBorderStyle = StrBorderStyle;
        }

        public double getX() {
            return X;
        }

        public void setX(double X) {
            this.X = X;
        }

        public double getY() {
            return Y;
        }

        public void setY(double Y) {
            this.Y = Y;
        }

        public double getW() {
            return W;
        }

        public void setW(double W) {
            this.W = W;
        }

        public double getH() {
            return H;
        }

        public void setH(double H) {
            this.H = H;
        }

        public int getStandardDirection() {
            return StandardDirection;
        }

        public void setStandardDirection(int StandardDirection) {
            this.StandardDirection = StandardDirection;
        }

        public RollingBedBean getRollingBed() {
            return RollingBed;
        }

        public void setRollingBed(RollingBedBean RollingBed) {
            this.RollingBed = RollingBed;
        }

        public String getChartLabel() {
            return ChartLabel;
        }

        public void setChartLabel(String ChartLabel) {
            this.ChartLabel = ChartLabel;
        }

        public String getPointName() {
            return PointName;
        }

        public void setPointName(String PointName) {
            this.PointName = PointName;
        }

        public String getStrBins() {
            return StrBins;
        }

        public void setStrBins(String StrBins) {
            this.StrBins = StrBins;
        }

        public int getRotateAngle() {
            return RotateAngle;
        }

        public void setRotateAngle(int RotateAngle) {
            this.RotateAngle = RotateAngle;
        }

        public List<PointValueColBeanX> getPointValueCol() {
            return PointValueCol;
        }

        public void setPointValueCol(List<PointValueColBeanX> PointValueCol) {
            this.PointValueCol = PointValueCol;
        }

        public List<?> getPointValueOptions() {
            return PointValueOptions;
        }

        public void setPointValueOptions(List<?> PointValueOptions) {
            this.PointValueOptions = PointValueOptions;
        }

        public static class RollingBedBean {
            /**
             * ChartLabel : RB name
             * StandardDirection : 0
             * PointName : RB point
             * RotateAngle : 0
             * PointValue : null
             * PointValueCol : [{"Key":"IsAuto","Value":"0","Value_bool":false},{"Key":"IsManual","Value":"1","Value_bool":false},{"Key":"IsPrepairing","Value":"2","Value_bool":false},{"Key":"WarningC","Value":"3","Value_bool":false},{"Key":"WarningB","Value":"4","Value_bool":false},{"Key":"CommingIn","Value":"9","Value_bool":false},{"Key":"CommingOut","Value":"20","Value_bool":false},{"Key":"EntryClosed","Value":"10","Value_bool":false},{"Key":"FWDRotatingHighSpeed","Value":"12","Value_bool":false},{"Key":"REVRotatingHighSpeed","Value":"13","Value_bool":false},{"Key":"FWDRotatingLowSpeed","Value":"14","Value_bool":false},{"Key":"REVRotatingLowSpeed","Value":"15","Value_bool":false},{"Key":"Exists","Value":"16","Value_bool":false},{"Key":"ExitClosed","Value":"18","Value_bool":false},{"Key":"IsVoidMode","Value":"31","Value_bool":false}]
             * PointValueOptions : []
             * ItemType : RollingBed
             * X : 0.0
             * Y : 0.0
             * W : 0.0
             * H : 0.0
             */

            private String ChartLabel;
            private int StandardDirection;
            private String PointName;
            private int RotateAngle;
            private Object PointValue;
            private String ItemType;
            private double X;
            private double Y;
            private double W;
            private double H;
            private List<PointValueColBean> PointValueCol;
            private List<?> PointValueOptions;

            public String getChartLabel() {
                return ChartLabel;
            }

            public void setChartLabel(String ChartLabel) {
                this.ChartLabel = ChartLabel;
            }

            public int getStandardDirection() {
                return StandardDirection;
            }

            public void setStandardDirection(int StandardDirection) {
                this.StandardDirection = StandardDirection;
            }

            public String getPointName() {
                return PointName;
            }

            public void setPointName(String PointName) {
                this.PointName = PointName;
            }

            public int getRotateAngle() {
                return RotateAngle;
            }

            public void setRotateAngle(int RotateAngle) {
                this.RotateAngle = RotateAngle;
            }

            public Object getPointValue() {
                return PointValue;
            }

            public void setPointValue(Object PointValue) {
                this.PointValue = PointValue;
            }

            public String getItemType() {
                return ItemType;
            }

            public void setItemType(String ItemType) {
                this.ItemType = ItemType;
            }

            public double getX() {
                return X;
            }

            public void setX(double X) {
                this.X = X;
            }

            public double getY() {
                return Y;
            }

            public void setY(double Y) {
                this.Y = Y;
            }

            public double getW() {
                return W;
            }

            public void setW(double W) {
                this.W = W;
            }

            public double getH() {
                return H;
            }

            public void setH(double H) {
                this.H = H;
            }

            public List<PointValueColBean> getPointValueCol() {
                return PointValueCol;
            }

            public void setPointValueCol(List<PointValueColBean> PointValueCol) {
                this.PointValueCol = PointValueCol;
            }

            public List<?> getPointValueOptions() {
                return PointValueOptions;
            }

            public void setPointValueOptions(List<?> PointValueOptions) {
                this.PointValueOptions = PointValueOptions;
            }

            public static class PointValueColBean {
                /**
                 * Key : IsAuto
                 * Value : 0
                 * Value_bool : false
                 */

                private String Key;
                private String Value;
                private boolean Value_bool;

                public String getKey() {
                    return Key;
                }

                public void setKey(String Key) {
                    this.Key = Key;
                }

                public String getValue() {
                    return Value;
                }

                public void setValue(String Value) {
                    this.Value = Value;
                }

                public boolean isValue_bool() {
                    return Value_bool;
                }

                public void setValue_bool(boolean Value_bool) {
                    this.Value_bool = Value_bool;
                }
            }
        }

        public static class PointValueColBeanX {
            /**
             * Key : IsAuto
             * Value : 0
             * Value_bool : false
             */

            private String Key;
            private String Value;
            private boolean Value_bool;

            public String getKey() {
                return Key;
            }

            public void setKey(String Key) {
                this.Key = Key;
            }

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }

            public boolean isValue_bool() {
                return Value_bool;
            }

            public void setValue_bool(boolean Value_bool) {
                this.Value_bool = Value_bool;
            }
        }
    }
}
