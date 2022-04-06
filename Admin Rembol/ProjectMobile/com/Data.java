package ProjectMobile.com;

public class Data {

    private String jenismobil, namamobil, hargamobil,idList, idMobil, statusmobil;

    Data(String jenismobil, String namamobil, String hargamobil, String idList, String idMobil, String statusmobil) {

        this.setJenismobil(jenismobil);
        this.setNamamobil(namamobil);
        this.setHargamobil(hargamobil);
        this.setIdList(idList);
        this.setIdMobil(idMobil);
        this.setStatusmobil(statusmobil);

    }

    public String getStatusmobil() {
        return statusmobil;
    }

    public void setStatusmobil(String statusmobil) {
        this.statusmobil = statusmobil;
    }

    public String getIdList() {
        return idList;
    }

    public void setIdList(String idList) {
        this.idList = idList;
    }

    public String getIdMobil() {
        return idMobil;
    }

    public void setIdMobil(String idMobil) {
        this.idMobil = idMobil;
    }


    public String getNamamobil() {
        return namamobil;
    }

    public void setNamamobil(String namamobil) {
        this.namamobil = namamobil;
    }

    public String getJenismobil() {
        return jenismobil;
    }

    public void setJenismobil(String jenismobil) {
        this.jenismobil = jenismobil;
    }

    public String getHargamobil() {
        return hargamobil;
    }

    public void setHargamobil(String hargamobil) {
        this.hargamobil = hargamobil;
    }



}


