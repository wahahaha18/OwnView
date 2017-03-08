package com.example.sun0002.ownview.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sun0002 on 2017/3/8.
 */

public class DianZhi {
    /**
     * a : 0
     * b : {"sample point":["2","1","00000000000000000000000000000000"]}
     */

    private String a;
    private BBean b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public BBean getB() {
        return b;
    }

    public void setB(BBean b) {
        this.b = b;
    }

    public static class BBean {
        @SerializedName("sample point")
        private List<String> _$SamplePoint265; // FIXME check this code

        public List<String> get_$SamplePoint265() {
            return _$SamplePoint265;
        }

        public void set_$SamplePoint265(List<String> _$SamplePoint265) {
            this._$SamplePoint265 = _$SamplePoint265;
        }
    }
}
