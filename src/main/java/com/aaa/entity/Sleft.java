package com.aaa.entity;

public class Sleft {
        private Integer clableid;
        private String clablename;
        private String tname;
        private String cpic;
        private String foundtime;
        private String synopsis;
        private String username;

        public Sleft(Integer clableid, String clablename, String tname, String cpic, String foundtime, String synopsis, String username) {
            this.clableid = clableid;
            this.clablename = clablename;
            this.tname = tname;
            this.cpic = cpic;
            this.foundtime = foundtime;
            this.synopsis = synopsis;
            this.username = username;
        }

        public Sleft() {
        }

        @Override
        public String toString() {
            return "left{" +
                    "clableid=" + clableid +
                    ", clablename='" + clablename + '\'' +
                    ", tname='" + tname + '\'' +
                    ", cpic='" + cpic + '\'' +
                    ", foundtime='" + foundtime + '\'' +
                    ", synopsis='" + synopsis + '\'' +
                    ", username='" + username + '\'' +
                    '}';
        }

        public Integer getClableid() {
            return clableid;
        }

        public void setClableid(Integer clableid) {
            this.clableid = clableid;
        }

        public String getClablename() {
            return clablename;
        }

        public void setClablename(String clablename) {
            this.clablename = clablename;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getCpic() {
            return cpic;
        }

        public void setCpic(String cpic) {
            this.cpic = cpic;
        }

        public String getFoundtime() {
            return foundtime;
        }

        public void setFoundtime(String foundtime) {
            this.foundtime = foundtime;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
}
