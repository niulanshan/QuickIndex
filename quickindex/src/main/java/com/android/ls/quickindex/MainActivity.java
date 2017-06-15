package com.android.ls.quickindex;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.quickindex)
    QuickIndex quickindex;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.circle)
    RelativeLayout circle;
    private ArrayList<Friend> data;
    private boolean isFromQuick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        data = new ArrayList<>();
        initData();
        Collections.sort(data);
        final MainAdapter mainAdapter = new MainAdapter(data);
        quickindex.setOnQuickTouchLinstener(new QuickIndex.QuickTouchLinstener() {
            @Override
            public void onDown(String s) {
                for (int i = 0; i < data.size(); i++) {
                    text.setText(s);
                    text.setVisibility(View.VISIBLE);
                    circle.setVisibility(View.VISIBLE);

                    isFromQuick = true;
                    if (PinyinUtils.getFirstLetter(data.get(i).pinyin).equals(s)) {
                        listview.setSelection(i);

                        break;
                    }
                }
            }

            @Override
            public void onRelease() {
                text.setVisibility(View.GONE);
                circle.setVisibility(View.GONE);
            }
        });
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                isFromQuick = false;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(!isFromQuick){
                    String firstLetter = PinyinUtils.getFirstLetter(data.get(firstVisibleItem).pinyin);
                    quickindex.setSelectedLetter(firstLetter);
                }

            }
        });


        listview.setAdapter(mainAdapter);


    }

    private void initData() {
        data.add(new Friend("刘德华"));
        data.add(new Friend("成龙"));
        data.add(new Friend("周星驰"));
        data.add(new Friend("周润发"));
        data.add(new Friend("安迪"));
        data.add(new Friend("白龙马"));
        data.add(new Friend("百变星君"));
        data.add(new Friend("陈家成"));
        data.add(new Friend("达尔文"));
        data.add(new Friend("方世玉"));
        data.add(new Friend("鸣人"));
        data.add(new Friend("卡斯特罗"));
        data.add(new Friend("赫卡里姆"));
        data.add(new Friend("萨尔"));
        data.add(new Friend("李时珍"));
        data.add(new Friend("东伯雪鹰"));
        data.add(new Friend("马里奥"));
        data.add(new Friend("赫敏"));
        data.add(new Friend("达尔文1"));
        data.add(new Friend("方世玉1"));
        data.add(new Friend("鸣人1"));
        data.add(new Friend("卡斯特罗1"));
        data.add(new Friend("赫卡里姆1"));
        data.add(new Friend("萨尔1"));
        data.add(new Friend("李时珍1"));
        data.add(new Friend("东伯雪鹰1"));
        data.add(new Friend("马里奥1"));
        data.add(new Friend("赫敏1"));

    }
}
