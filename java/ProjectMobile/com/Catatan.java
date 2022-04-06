package ProjectMobile.com;

public class Catatan {
    private long _id;
    private String _catatan;

    // method untuk set id barang
    public void setID(long id) {
        this._id = id;
    }

    // method untuk mendapatkan id barang
    public long getID() {
        return this._id;
    }

    //method untuk set nama barang
    public void set_catatan(String catatanadmin) {
        this._catatan = catatanadmin;
    }

    // method untuk mendapatkan nama barang
    public String get_catatan() {
        return this._catatan;
    }

    @Override
    public String toString() {
        return "Catatan\t\t\t\t: " + _catatan;
    }
}
