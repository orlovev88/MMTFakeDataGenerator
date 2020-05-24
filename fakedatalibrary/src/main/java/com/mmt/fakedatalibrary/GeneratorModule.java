package com.mmt.fakedatalibrary;

import com.mmt.fakedatalibrary.models.MetricActivity;
import com.mmt.fakedatalibrary.models.MetricHRV;
import com.mmt.fakedatalibrary.models.MetricPPG;
import com.mmt.fakedatalibrary.models.MetricSleep;
import com.mmt.fakedatalibrary.models.MetricWorkout;

import io.realm.annotations.RealmModule;

@RealmModule(
        library = true,
        classes = {
                MetricActivity.class,
                MetricHRV.class,
                MetricPPG.class,
                MetricSleep.class,
                MetricWorkout.class
        }
)
public class GeneratorModule {
}
