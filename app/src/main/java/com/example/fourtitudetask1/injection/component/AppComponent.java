package com.example.fourtitudetask1.injection.component;

import android.app.Application;
import android.content.Context;

import com.example.fourtitudetask1.AppApplication;
import com.example.fourtitudetask1.base.fragment.BaseMvpFragment;
import com.example.fourtitudetask1.injection.context.ApplicationContext;
import com.example.fourtitudetask1.injection.module.AppModule;
import com.example.fourtitudetask1.ui.main.TriviaMainActivity;
import com.example.fourtitudetask1.ui.main.TriviaMainFragment;
import com.example.fourtitudetask1.ui.question.QuestionFragment;
import com.example.fourtitudetask1.ui.questionCount.QuestionCountFragment;
import com.example.fourtitudetask1.util.StaticUtil;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    /*Activty*/
//    void inject(BaseActivity activity);

//    void inject(BaseMvpActivity activity);

    void inject(TriviaMainActivity activity);


    /*Fragment*/

    void inject(BaseMvpFragment fragment);

    void inject(TriviaMainFragment fragment);

    void inject(QuestionCountFragment fragment);

    void inject(QuestionFragment fragment);

    void inject(AppApplication mApplication);

    Application application();

    @ApplicationContext
    Context context();

    StaticUtil appUtil();
}
