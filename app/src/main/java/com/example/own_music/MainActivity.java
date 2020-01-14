package com.example.own_music;

import android.Manifest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;

import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.karumi.dexter.Dexter.*;


public class MainActivity extends AppCompatActivity{
    TabHost tabHost;
    ListView list;
    ListView gridsinger,listtk,listnumber;
    ArrayList<song> song;
    String[] thongke;
    ArrayList<String> display,singer,tk;
    ArrayAdapter<String> adapter,listsing;
    int i=0,checkchose=0,plaf=0;
    ArrayList<File> listfile=new ArrayList<>();
    playsong playsong;
    EditText search;
    TextView timerun,duration,namesong;
    SeekBar process;
    Button statistical;
    ImageButton play,prev,next,back,change;
    private Handler threadHandler=new Handler();
    custom custom;
    list_statistical list_statistical;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=(ListView)findViewById(R.id.listsong);
        gridsinger=(ListView)findViewById(R.id.grib);
        listnumber=(ListView)findViewById(R.id.listnhac);
        tabHost=(TabHost)findViewById(R.id.tabhost);
        timerun=(TextView)findViewById(R.id.time);
        duration=(TextView)findViewById(R.id.alltime);
        play=(ImageButton)findViewById(R.id.play);
        prev=(ImageButton)findViewById(R.id.prev);
        next=(ImageButton)findViewById(R.id.next);
        search=(EditText)findViewById(R.id.search);
        back=(ImageButton)findViewById(R.id.back);
        namesong=(TextView) findViewById(R.id.txtnamesong);
        process=(SeekBar)findViewById(R.id.process);
        change=(ImageButton)findViewById(R.id.change);
        statistical=(Button)findViewById(R.id.btnthongke);
        playsong=new playsong();
        tabHost.setup();
        TabHost.TabSpec tabSpec1=tabHost.newTabSpec("t1");
        tabSpec1.setIndicator("music");
        tabSpec1.setContent(R.id.tab1);
        tabHost.addTab(tabSpec1);
        final TabHost.TabSpec tabSpec2=tabHost.newTabSpec("t2");
        tabSpec2.setIndicator("song");
        tabSpec2.setContent(R.id.tab2);
        tabHost.addTab(tabSpec2);
        TabHost.TabSpec tabSpec3=tabHost.newTabSpec("t3");
        tabSpec3.setIndicator("singer");
        tabSpec3.setContent(R.id.tab3);
        tabHost.addTab(tabSpec3);
        TabHost.TabSpec tabSpec4=tabHost.newTabSpec("t4");
        tabSpec4.setContent(R.id.tab4);
        tabSpec4.setIndicator("statistical");
        tabHost.addTab(tabSpec4);
        requestaccess();
        thongke=new String[song.size()];
        for(int h=0;h<song.size();h++)
        {
            thongke[h]="0";
        }
        playsong.setdatasourse(song.get(0).getParth());
        singer=createelement();
        displaysinger(createelement());
         hideback();
        statistical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            hienthi();
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().isEmpty())
                {
                    listfile.clear();
                    display.clear();
                    setDisplay();
                }
                else {
                    display.clear();
                    setDisplay();
                    ArrayList<String> songsing=new ArrayList<>();
                    for(String item:display) {
                        String h= item.substring(0,s.length());
                        if (h.equals(s.toString()))
                        {
                            songsing.add(item);
                        }
                    }
                    display.clear();
                    display=songsing;
                    displaylistview();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               for(int j=0;j<song.size();j++)
               {
                   if(display.get(position).equals(song.get(j).getName()+"\n"+song.get(j).getsinger())) {
                       i=j;
                       break;
                   }

               }
                checkchose=1;
                try {
                    if(playsong.check()) {
                        playsong.setstop();
                        playsong();

                    }
                    else

                        playsong();
                    play.setImageResource(R.drawable.pause);
                    tabHost.setCurrentTab(0);
                    abc(i);
                }
                catch (Exception ex)
                {}


            }
        });
       gridsinger.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              // fillsongsinger=new ArrayList<>();
               if(!back.isEnabled()) {
                   listfile.clear();
                   setDisplay();
                   singer.clear();
                   for (int i = 0; i < song.size(); i++) {
                       if (song.get(i).getsinger().equals(custom.getItem(position).toString())) {
                           singer.add(song.get(i).getName() + "\n" + song.get(i).getsinger());
                          // fillsongsinger.add(song.get(i));
                       }
                   }
                   showsong();
                   viewback();
                   //song.clear();
                   //song=fillsongsinger;
               }
               else
               {


                   for(int j=0;j<song.size();j++)
                   {
                       if(singer.get(position).equals(song.get(j).getName()+"\n"+song.get(j).getsinger())) {
                           i = j;
                           break;
                       }
                   }
                   checkchose=1;
                   try {
                       if(playsong.check()) {
                           playsong.setstop();
                           playsong();

                       }
                       else

                           playsong();
                       play.setImageResource(R.drawable.pause);

                       abc(i);
                   }
                   catch (Exception ex)
                   {}


               }
           }
       });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playsong.check()) {
                    playsong.setpause();
                    play.setImageResource(R.drawable.play);
                }
                else
                if(checkchose==1)
                {
                    threadHandler=new Handler();
                    int getprocess=process.getProgress()*1000;
                    playsong.setseckto(getprocess);
                    playsong.playsong();
                    play.setImageResource(R.drawable.pause);
                    UpdateSeekBarThread updateSeekBarThread= new UpdateSeekBarThread();
                    threadHandler.postDelayed(updateSeekBarThread,50);}
                if(checkchose==0)
                {
                    playsong();
                    play.setImageResource(R.drawable.pause);
                    threadHandler=new Handler();
                    play.setImageResource(R.drawable.pause);
                    UpdateSeekBarThread updateSeekBarThread= new UpdateSeekBarThread();
                    threadHandler.postDelayed(updateSeekBarThread,50);
                    checkchose=1;
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playsong.setstop();
                if(i==song.size()-1)
                {
                    i=0;

                }
                else {
                    i++;
                }
                process.setProgress(0);
                playsong();
                play.setImageResource(R.drawable.pause);
                abc(i);

            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playsong.setstop();
                if(i>0)
                {
                    i--;

                }
                else {
                    i = song.size()-1;
                }
                process.setProgress(0);
                playsong();
                play.setImageResource(R.drawable.pause);
                abc(i);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displaysinger(createelement());
                hideback();
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(plaf==0)
                {
                    change.setImageResource(R.drawable.loopsong);
                    plaf++;
                   show("loop the song");
                }
                else
                if(plaf==1)
                {
                    change.setImageResource(R.drawable.megersong);
                    plaf++;
                    show("mix the song");
                }
                else
                if (plaf==2)
                {
                    change.setImageResource(R.drawable.nextsong);
                    plaf=0;
                    show("sequence the song");
                }
            }
        });
        process.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress==playsong.getduration()/1000)
                {
                    if(plaf==0)
                    {
                        process.setProgress(0);
                        playsong.setstop();
                        if(i==song.size()-1)
                        {
                            i=0;

                        }
                        else {
                            i++;
                        }
                        playsong();
                        play.setImageResource(R.drawable.pause);
                    }
                    else
                    if(plaf==1)
                    {
                        process.setProgress(0);
                        playsong.playsong();
                        play.setImageResource(R.drawable.pause);
                    }
                    else
                    if(plaf==2)
                    {
                        process.setProgress(0);
                        Random changei=new Random();
                        i=changei.nextInt(song.size()-1);
                        playsong.setstop();
                        playsong();
                        play.setImageResource(R.drawable.pause);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int getprocess=process.getProgress()*1000;
                playsong.setseckto(getprocess);
                playsong.playsong();
                play.setImageResource(R.drawable.pause);
            }
        });

        // createelement();

    }

    private void hienthi() {
      /*  tk=new ArrayList<>();
       for (int k=0;k<song.size();k++)
        {
            String a=display.get(k);
            String b=thongke[k];
            tk.add(a+"\t\t\t\t\t"+":"+b);
        }
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tk);
        listnumber.setAdapter(adapter);*/
        list_statistical listStatistical=new list_statistical(this,display,thongke);
        listnumber.setAdapter(listStatistical);
    }

    public void show(String text)
    {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    private void hideback() {
        back.setVisibility(View.GONE);
        back.setEnabled(false);
    }

    private void viewback() {
        back.setVisibility(View.VISIBLE);
        back.setEnabled(true);
    }

    private void showsong() {
        listsing=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,singer);
        gridsinger.setAdapter(listsing);
    }

    private void displaysinger(ArrayList<String> createelement) {

        custom=new custom(this,createelement);
        gridsinger.setAdapter(custom);
    }


    public void requestaccess()

    {
        withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener()
                {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {
                        setDisplay();
                    }
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {

                    }
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }
    public void getsong(File file)
    {
        ArrayList<File> listfile=new ArrayList<>();
        File[] files=file.listFiles();
        for(File f:files)
        {
            if(f.isDirectory())
            {
                listfile.addAll(getfilesong(f));


            }
            else
            if (f.getName().endsWith(".mp3"))
                this.listfile.add(f);
        }
    }

    private ArrayList<File> getfilesong(File f) {

        File[] files=f.listFiles();
        for(File fi:files)
        {
            if(fi.isDirectory()&&fi.isHidden())
            {

            }
            else
            if (fi.getName().endsWith(".mp3"))
                this.listfile.add(fi);
        }
        return this.listfile;
    }

    public void setDisplay()
    {
        this.listfile.clear();
        // ArrayList<File> allsong=getsong(Environment.getExternalStorageDirectory());
        getsong(Environment.getExternalStorageDirectory());
        song=new ArrayList<>();
        display=new ArrayList<>();
        display.clear();
        String name,substring,singer;
        for (int i=0;i<this.listfile.size();i++)
        {
            name=getnamesong(listfile.get(i).getName());
            singer=getsinger(listfile.get(i).getName());
            song.add(new song(name,listfile.get(i).getPath(),singer));
            display.add(name+"\n"+singer);
            //Toast.makeText(this," "+listfile.get(i).getName(),Toast.LENGTH_SHORT).show();
        }
        displaylistview();

    }
    public void displaylistview()
    {
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,display);
        list.setAdapter(adapter);
    }
    public String getsinger(String namesong)
    {
        String singer="Unknow";
        try {
            String[] name = namesong.split("-");
            if(name[1].endsWith(".mp3"))
                singer = name[1].substring(0, name[1].length() - 4);
            else
                singer=name[1];
            try{
                String[] namesinger=singer.split("_");
                singer=namesinger[0];
            }
            catch (Exception ex)
            {}
        }
        catch (Exception e)
        {

        }
        return singer;
    }
    public  String getnamesong(String song)
    {
        String namesong=song.substring(0, song.length() - 4);
        try {
            String[] name = song.split("-");
            if(name[1]!=" ")
                namesong=name[0];
        }
        catch (Exception e)
        {

        }
        return namesong;
    }
    class UpdateSeekBarThread implements Runnable {
        public void run()
        {

            process.setMax(playsong.getduration() / 1000);
            int currentPosition = playsong.getCurrentPosition();
            String currentPositionStr = changetime(currentPosition);
            timerun.setText(currentPositionStr);
            process.setProgress(currentPosition / 1000);
            threadHandler.postDelayed(this, 50);
        }
    }
    public String changetime(int time ) {

        Long min = TimeUnit.MILLISECONDS.toMinutes((long) time);
        Long sec = TimeUnit.MILLISECONDS.toSeconds((long) time);
        sec = sec % 60;

        return "0"+min + ":" + sec;
    }
    public void playsong()
    {
        playsong.setdatasourse(song.get(i).getParth());
        namesong.setText(song.get(i).getName()+"-"+song.get(i).getsinger());
       // process.setProgress(playsong.getcurrenpossition());
        playsong.setplay();
        duration.setText(changetime(playsong.getduration()));
        UpdateSeekBarThread updateSeekBarThread= new UpdateSeekBarThread();
        threadHandler.postDelayed(updateSeekBarThread,50);
        tabHost.setCurrentTab(0);

    }
    public ArrayList<String> createelement()
    {
        setDisplay();
        ArrayList<String> arraysing=new ArrayList<>();
        try {
            for ( int i = 0; i < song.size(); i++) {
                arraysing.add(song.get(i).getsinger());
            }
            for(int i=0;i<arraysing.size();i++)
            {
                for(int j=0;j<arraysing.size();j++) {
                    if (arraysing.get(i).equals(arraysing.get(j)))
                    {
                        arraysing.remove(j);
                    }
                }

            }
        }
        catch (Exception ex)
        {}
        return arraysing;
    }
    public void abc(int a)
    {
        thongke[a]=String.valueOf(Integer.valueOf(thongke[a])+1);
    }
}
