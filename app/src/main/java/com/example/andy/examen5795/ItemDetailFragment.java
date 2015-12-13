package com.example.andy.examen5795;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.andy.examen5795.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;
    private onItemSelectedListener listener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.content);

            Button boton=(Button)rootView.findViewById(R.id.boton);
            boton.setOnClickListener(new View.OnClickListener(){//onListener Creado e implementa el metodo onCLick por defecto.
//creado por defecto
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
                @Override
                public void onClick(View v) {
                    borrar();
                    //si esta en vertical se cerrara
                    if (rootView==null||rootView.isInLayout()){
                        getActivity().finish();

                    }
                }
            }
            );   }

        listener.send("OK");
        return rootView;
    }
    //Este metodo borrara el contenido del textview
    public void borrar(){
        TextView tv=(TextView)getView().findViewById(R.id.item_detail);
        tv.setText("");//para q nos limpie el boton
    }
    public interface onItemSelectedListener{
        public void send(String mensaje);
    }
    public void onAttach(Activity context){
        super.onAttach(context);

        if(context instanceof onItemSelectedListener){
            listener=(onItemSelectedListener)context;
        }
    }
}
