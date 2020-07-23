package com.ysj.myfirstopendemo;

/**
 * Created by tekabo
 * Created on 2020/7/23
 * PackageName com.ysj.myfirstopendemo
 */
public class MyEventMessage {

        private String type;
        private String message;

        public MyEventMessage() {
        }

        public MyEventMessage(String type, String message) {
            this.type = type;
            this.message = message;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "MyEventMessage{" +
                    "type='" + type + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }

}
