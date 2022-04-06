package com.example.testrembol;

public class Data {

    private String nmobil, alamat, rtelp, ridlist, rstat, ridorder;

    Data( String nmobil, String alamat,  String rtelp, String ridlist, String rstat, String ridorder){

        this.setAlamat(alamat);
        this.setNmobil(nmobil);
        this.setRtelp(rtelp);
        this.setRidlist(ridlist);
        this.setRstat(rstat);
        this.setRidorder(ridorder);

    }

    public String getRidorder() {
        return ridorder;
    }

    public void setRidorder(String ridorder) {
        this.ridorder = ridorder;
    }

    public String getRidlist() {
        return ridlist;
    }

    public void setRidlist(String ridlist) {
        this.ridlist = ridlist;
    }

    public String getRstat() {
        return rstat;
    }

    public void setRstat(String rstat) {
        this.rstat = rstat;
    }

    public String getNmobil() {
        return nmobil;
    }

    public void setNmobil(String nmobil) {
        this.nmobil = nmobil;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getRtelp() {
        return rtelp;
    }

    public void setRtelp(String rtelp) {
        this.rtelp = rtelp;
    }


}
