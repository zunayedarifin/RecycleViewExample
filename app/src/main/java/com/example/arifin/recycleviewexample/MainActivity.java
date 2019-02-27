package com.example.arifin.recycleviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class MainActivity extends AppCompatActivity implements RecycleViewAdapter.ItemClickListener {

    RecycleViewAdapter adapter;
    public static List<RecyclerViewCustomClass> rowname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreateBlankDocument();
        Date currentTime = Calendar.getInstance().getTime();
        String time= currentTime.toString();
        time=time.substring(10,time.length());
        time=time.substring(0,time.length() - 18);

        //data to populate the RecyclerView with
        rowname = new ArrayList<RecyclerViewCustomClass>();
        rowname.add(new RecyclerViewCustomClass("row1.1","row1.2",time));
        rowname.add(new RecyclerViewCustomClass("row2.1","row2.2",time));
        rowname.add(new RecyclerViewCustomClass("row3.1","row3.2",time));
        rowname.add(new RecyclerViewCustomClass("row4.1","row4.2",time));
        rowname.add(new RecyclerViewCustomClass("row5.1","row5.2",time));

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecycleViewAdapter(this, rowname);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    public void CreateBlankDocument(){
        Toast.makeText(getBaseContext(),
                "Creating Blank Document",
                Toast.LENGTH_LONG).show();
        try{
            //Create instance of DocumentBuilderFactory
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            //Get the DocumentBuilder
            DocumentBuilder parser = factory.newDocumentBuilder();
            //Create blank DOM Document
            Document doc=parser.newDocument();
            //create the root element
            Element root=doc.createElement("root");
            //all it to the xml tree
            doc.appendChild(root);
            Element childelement=doc.createElement("Entry1");
            //Add the attribute to the child
            childelement.setAttribute("value", "1");
            childelement.setAttribute("Value2","2");
            root.appendChild(childelement);


            //create a comment
            //Comment comment=doc.createComment("This is a comment");
            //add in the root element
            //root.appendChild(comment);
            //creat child element
            //Element childelement=doc.createElement("Entry1");
            //Add the attribute to the child
            //childelement.setAttribute("value", "1");
            //root.appendChild(childelement);



            TransformerFactory transformerfactory=
                    TransformerFactory.newInstance();
            Transformer transformer=
                    transformerfactory.newTransformer();

            DOMSource source=new DOMSource(doc);
            FileOutputStream _stream=getApplicationContext().openFileOutput("NewDom.xml", getApplicationContext().MODE_WORLD_WRITEABLE);
            StreamResult result=new StreamResult(_stream);
            transformer.transform(source, result);
            Toast.makeText(getBaseContext(),
                    "Done File created",
                    Toast.LENGTH_LONG).show();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        //rToast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        try {
            FileInputStream fin = openFileInput("NewDom.xml");
            int c;
            String temp="";
            while( (c = fin.read()) != -1){
                temp = temp + Character.toString((char)c);
            }
            Log.d("result",temp);
            //tv.setText(temp);
            Toast.makeText(getBaseContext(),"file read :"+temp,Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
        }
    }
}
