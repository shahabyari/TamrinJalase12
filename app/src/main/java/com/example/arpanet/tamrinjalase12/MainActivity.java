package com.example.arpanet.tamrinjalase12;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public TextView outputText;
    ListView lsa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lsa = (ListView) findViewById(R.id.List);
        Fech_Android_Contacts();

    }

    public class A_Contact {
        public String Name;
        public String Number;
        public int id = 0;
    }

    public void Fech_Android_Contacts()

    {
        ArrayList<A_Contact> d = new ArrayList<A_Contact>();

        Cursor cd = null;
        ContentResolver cr = getContentResolver();
        try {
            cd = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        } catch (Exception e) {
            Log.e("Error = ", e.toString());
        }

        if (cd.getCount() > 0) {
            while (cd.moveToNext()) {

                A_Contact ac = new A_Contact();
                String id = cd.getString(cd.getColumnIndex(ContactsContract.Contacts._ID));
                String Name = cd.getString(cd.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                ac.Name = Name;

                d.add(ac);


            }

            Adapterlistcontact Adap = new Adapterlistcontact(this,d);
            lsa.setAdapter(Adap);
        }

    }

    public class Adapterlistcontact extends BaseAdapter {

        Context mcontext;
        ArrayList<A_Contact> ac;

        public Adapterlistcontact(Context ct,ArrayList<A_Contact> Acontext ) {
        mcontext = ct;
        ac = Acontext;
        }


        @Override
        public int getCount() {
            return ac.size();
        }

        @Override
        public Object getItem(int i) {
            return ac.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            View v = View.inflate(mcontext,R.layout.row1,null);
            TextView tvname = (TextView) v.findViewById(R.id.contactName);
            TextView tvnumber = (TextView) v.findViewById(R.id.contactNumber);
            tvname.setText(ac.get(i).Name);
            tvnumber.setText(ac.get(i).Number);
            return v;
        }
    }


}


