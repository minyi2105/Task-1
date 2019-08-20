package com.example.fourtitudetask1.util;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.fourtitudetask1.R;
import com.example.fourtitudetask1.model.Dummy;
import com.example.fourtitudetask1.room.DummyDatabase;

import java.lang.ref.WeakReference;
import java.util.List;

public class DummyDbUtil {
    public static class AddDummy extends AsyncTask<Void, Void, Void> {
        private Dummy dummy;
        private WeakReference<Context> context;

        public AddDummy(Context context, Dummy dummy) {
            this.context = new WeakReference<>(context);
            this.dummy = dummy;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            DummyDatabase dummyDatabase = DummyDatabase.getAppDatabase(context.get());
            dummyDatabase.dummyDao().insert(dummy);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context.get(), context.get().getResources().getString(R.string.new_dummy_created), Toast.LENGTH_SHORT).show();
            ((Activity) context.get()).finish();
        }
    }

    public static class AddAllDummies extends AsyncTask<Void, Void, Void>{
        private List<Dummy> dummies;
        private WeakReference<Context> context;
        public AsyncResponse delegate = null;

        public AddAllDummies(Context context, List<Dummy> dummies){
            this.context = new WeakReference<>(context);
            this.dummies = dummies;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            DummyDatabase dummyDatabase = DummyDatabase.getAppDatabase(context.get());
            dummyDatabase.dummyDao().insertAllDummies(dummies);

            delegate.populatedFinish();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public static class UpdateDummy extends AsyncTask<Void, Void, Void> {
        private Dummy dummy;
        private WeakReference<Context> context;

        public UpdateDummy(Context context, Dummy dummy) {
            this.context = new WeakReference<>(context);
            this.dummy = dummy;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            DummyDatabase dummyDatabase = DummyDatabase.getAppDatabase(context.get());
            dummyDatabase.dummyDao().update(dummy);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context.get(), context.get().getResources().getString(R.string.dummy_updated_successfully), Toast.LENGTH_SHORT).show();
            ((Activity) context.get()).finish();
        }
    }

    public static class GetDummy extends AsyncTask<Void, Void, Dummy> {
        private WeakReference<Context> context;
        private int id;
        public AsyncResponse delegate = null;

        public GetDummy(Context context, int id) {
            this.context = new WeakReference<>(context);
            this.id = id;
        }

        @Override
        protected Dummy doInBackground(Void... voids) {
            DummyDatabase dummyDatabase = DummyDatabase.getAppDatabase(context.get());
            Dummy dummy = dummyDatabase.dummyDao().getDummy(id);
            return dummy;
        }

        @Override
        protected void onPostExecute(Dummy dummy) {
            super.onPostExecute(dummy);

            delegate.processFinish(dummy);
        }
    }

    public static class GetAllDummies extends AsyncTask<Void, Void, List<Dummy>> {
        private WeakReference<Context> context;
        public AsyncResponse delegate = null;

        public GetAllDummies(Context context) {
            this.context = new WeakReference<>(context);
        }

        @Override
        protected List<Dummy> doInBackground(Void... voids) {
            DummyDatabase dummyDatabase = DummyDatabase.getAppDatabase(context.get());
            return dummyDatabase.dummyDao().getAllDummies();
        }

        @Override
        protected void onPostExecute(List<Dummy> dummies) {
            super.onPostExecute(dummies);

            delegate.processFinish(dummies);
        }
    }
}
