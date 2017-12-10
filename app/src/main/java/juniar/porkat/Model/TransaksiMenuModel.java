package juniar.porkat.Model;

import org.joda.time.DateTime;

/**
 * Created by Nicolas Juniar on 07/12/2017.
 */

public class TransaksiMenuModel {
    MenuKateringModel menuKateringModel;
    DateTime jam_pengantaran;
    boolean visibility;
    int type;

    public TransaksiMenuModel() {
        this.menuKateringModel = new MenuKateringModel();
        this.visibility = false;
        this.jam_pengantaran=new DateTime();
        this.type = 1;
    }

    public MenuKateringModel getMenuKateringModel() {
        return menuKateringModel;
    }

    public void setMenuKateringModel(MenuKateringModel menuKateringModel) {
        this.menuKateringModel = menuKateringModel;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public DateTime getJam_pengantaran() {
        return jam_pengantaran;
    }

    public void setJam_pengantaran(DateTime jam_pengantaran) {
        this.jam_pengantaran = jam_pengantaran;
    }
}
