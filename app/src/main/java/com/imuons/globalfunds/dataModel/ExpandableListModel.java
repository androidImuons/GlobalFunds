package com.imuons.globalfunds.dataModel;
/*
 * Created by Tabish on 06-02-2020.
 */
public class ExpandableListModel {

    public int image;
    public String title;

    public ExpandableListModel(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {

        return image;
    }

    public String getTitle() {
        return title;
    }
    //    public static ArrayList<ExpandableListModel> expandableListModels()
//
//    {
//        ArrayList<ExpandableListModel> list1 = new ArrayList<>();
//
//        list1.add(new ExpandableListModel(R.drawable.ic_direct_user,"Summary"));
//        list1.add(new ExpandableListModel(R.drawable.ic_direct_user,"Profile"));
//        list1.add(new ExpandableListModel(R.drawable.ic_direct_user,"Genealogy"));
//        list1.add(new ExpandableListModel(R.drawable.ic_direct_user,"E-Pin"));
//        list1.add(new ExpandableListModel(R.drawable.ic_direct_user,"E-Wallet"));
//        list1.add(new ExpandableListModel(R.drawable.ic_direct_user,"Business Club"));
//        list1.add(new ExpandableListModel(R.drawable.ic_direct_user,"WithDrawal"));
//        list1.add(new ExpandableListModel(R.drawable.ic_direct_user,"Top Up"));
//        list1.add(new ExpandableListModel(R.drawable.ic_direct_user,"Support"));
//        list1.add(new ExpandableListModel(R.drawable.ic_direct_user,"Logout"));
//
//
//
//
//
//
//
//        return list1;
//    }
}
