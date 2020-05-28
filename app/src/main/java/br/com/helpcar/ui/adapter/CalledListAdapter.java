package br.com.helpcar.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.helpcar.R;
import br.com.helpcar.model.Called;
import br.com.helpcar.util.Image;

public class CalledListAdapter extends BaseAdapter {

    private final Context context;
    private List<Called> calleds;
    private String cardTitle;

    public CalledListAdapter(Context context, List<Called> calleds, String cardTitle) {
        this.context = context;
        this.calleds = calleds;
        this.cardTitle = cardTitle;
    }

    @Override
    public int getCount() {
        return calleds.size();
    }

    @Override
    public Object getItem(int position) {
        return calleds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_called, viewGroup, false);
        Called called = calleds.get(position);

        TextView fieldCalled = view.findViewById(R.id.titleCard_idCalled);
        addId(called, fieldCalled);
        TextView fieldBrand = view.findViewById(R.id.textCard_brandVehicle);
        addBrand(called, fieldBrand);
        TextView fieldModel = view.findViewById(R.id.textCard_modelVehicle);
        addModel(called, fieldModel);
        TextView fieldDescription = view.findViewById(R.id.textCard_descrption);
        addDescription(called, fieldDescription);

        ImageView imageCalledView =  view.findViewById(R.id.calledImage);
        if(called.getPhotoOfVehicle() != null) {
            imageCalledView.setVisibility(View.VISIBLE);
            addPhoto(called, imageCalledView);
        }
        return view;
    }

    private void addPhoto(Called called, ImageView imageCalledView) {
        String photoOfVehicle = called.getPhotoOfVehicle();
        imageCalledView.setImageBitmap(Image.decode(photoOfVehicle));
    }

    private void addId(Called called, TextView fieldCalled) {
        int id = called.getCalledId();
        fieldCalled.setText(cardTitle);
    }

    private void addDescription(Called called, TextView fieldDescrpition) {
        String description = called.getCalledDescription();
        fieldDescrpition.setText(description);
    }

    private void addModel(Called called, TextView fieldModel) {
        String model = called.getModelVehicle();
        fieldModel.setText(model);
    }

    private void addBrand(Called called, TextView fieldBrand) {
        String brand = called.getBrandVehicle();
        fieldBrand.setText(brand + "  -");
    }

    public void setCalleds(List<Called> calleds){
        this.calleds = calleds;
        notifyDataSetChanged();
    }
}
