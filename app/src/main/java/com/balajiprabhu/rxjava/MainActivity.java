package com.balajiprabhu.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    
    private Observable<Integer> integerObservable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        integerObservable = createDataSet();

        integerObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Integer, Double>() {
                    @Override
                    public Double apply(Integer integer) throws Exception {
                        return integer*10.0;
                    }
                })
                .subscribe(getDataSet());

    }


    private Observable<Integer> createDataSet() {

        return Observable.fromIterable(integerGenerator());
    }


    private Observer<Double> getDataSet(){

        return new Observer<Double>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Double integer) {
                Log.d(TAG, "onNext: "+integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };

    }



    private ArrayList integerGenerator(){

        ArrayList<Integer> integers = new ArrayList<>();

        Random random = new Random();

        for(int i = 0 ; i <= 20 ; i++){
            integers.add(random.nextInt(100));
        }

        return integers;
    }


}
