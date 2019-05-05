package com.example.mabdira.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.mabdira.roomwordsample.model.Word;
import com.example.mabdira.roomwordsample.model.WordDao;
import com.example.mabdira.roomwordsample.model.WordRoomDB;

import java.util.List;

public class WordRepository {

    private WordDao mWordDao;

    public WordRepository(Application application) {
        WordRoomDB db = WordRoomDB.getDatabase(application);
        mWordDao = db.wordDao();
    }

    public LiveData<List<Word>> getAllWords() {
        return mWordDao.getAllWords();
    }

    public void insert(Word word) {
        new InsertAsyncTask(mWordDao).execute(word);
    }

    public void deleteAll() {
        mWordDao.deleteAll();
    }

    /**
     * You must call this on a non-UI thread or your app will crash. Room ensures that you don't
     * do any long-running operations on the main thread, blocking the UI.
     */
    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        private InsertAsyncTask(WordDao wordDao) {
            mAsyncTaskDao = wordDao;
        }

        @Override
        protected Void doInBackground(final Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }

}
