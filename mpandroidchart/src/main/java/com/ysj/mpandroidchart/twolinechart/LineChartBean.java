package com.ysj.mpandroidchart.twolinechart;

import java.util.List;

/**
 * Created by tekabo
 * Created on 2020/8/6
 * PackageName com.ysj.mpandroidchart.linechart
 */
public class LineChartBean {
    private int ERRORNO;
    private GRID0Bean GRID0;

    public int getERRORNO() {
        return ERRORNO;
    }

    public void setERRORNO(int ERRORNO) {
        this.ERRORNO = ERRORNO;
    }

    public GRID0Bean getGRID0() {
        return GRID0;
    }

    public void setGRID0(GRID0Bean GRID0) {
        this.GRID0 = GRID0;
    }

    public class GRID0Bean {
        private int code;
        private String message;
        private ResultBean result;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public class ResultBean {

            private List<CompositeIndexBean> compositeIndexGEM;
            private List<IncomeBeans> incomeBeans;
            public List<IncomeBeans> getIncomeBeans() {
                return incomeBeans;
            }

            public void setIncomeBeans(List<IncomeBeans> incomeBeans) {
                this.incomeBeans = incomeBeans;
            }

            public List<CompositeIndexBean> getCompositeIndex1() {
                return compositeIndexGEM;
            }

            public void setCompositeIndex1(List<CompositeIndexBean> compositeIndex1) {
                this.compositeIndexGEM = compositeIndex1;
            }
        }

    }
}
