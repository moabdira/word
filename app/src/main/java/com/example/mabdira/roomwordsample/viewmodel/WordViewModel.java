package com.example.mabdira.roomwordsample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.mabdira.roomwordsample.WordRepository;
import com.example.mabdira.roomwordsample.model.Word;

import java.util.List;

/**
 * Warning: Never pass context into ViewModel instances. Do not store Activity, Fragment, or View
 * instances or their Context in the ViewModel.
 *
 * For example, an Activity can be destroyed and created many times during the lifecycle of a
 * ViewModel as the device is rotated. If you store a reference to the Activity in the ViewModel,
 * you end up with references that point to the destroyed Activity. This is a memory leak.
 *
 * If you need the application context, use AndroidViewModel, as shown in this codelab.
 *
 * Important: ViewModel is not a replacement for the onSaveInstanceState() method, because the
 * ViewModel does not survive a process shutdown. Learn more here
 * (https://medium.com/google-developers/viewmodels-persistence-onsaveinstancestate-restoring-ui-state-and-loaders-fc7cc4a6c090).
 */
public class WordViewModel extends AndroidViewModel {
    // reference to the word repository
    private WordRepository mRepository;
    // cache a list of words
    private LiveData<List<Word>> mAllWords;

    /**
     * Add a constructor that gets a reference to the repository
     * and gets the list of words from the repository.
     * @param application
     */
    public WordViewModel(Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    /**
     * Add a "getter" method for all the words.
     * This completely hides the implementation from the UI.
     */
    public LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    /**
     * Create a wrapper insert() method that calls the Repository's insert() method.
     * In this way, the implementation of insert() is completely hidden from the UI.
     */
    public void insertWord(Word word) {
        mRepository.insert(word);
    }

    /**
     * Create a wrapper delete() method that calls the Repository's delete() method.
     * In this way, the implementation of delete() is completely hidden from the UI.
     */
    public void deleteAllWords() {
        mRepository.deleteAll();
    }

}
