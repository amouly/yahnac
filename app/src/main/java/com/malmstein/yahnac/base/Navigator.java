package com.malmstein.yahnac.base;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

import com.malmstein.yahnac.HNewsActivity;
import com.malmstein.yahnac.comments.CommentsActivity;
import com.malmstein.yahnac.comments.CommentsFragment;
import com.malmstein.yahnac.connectivity.NetworkDetector;
import com.malmstein.yahnac.model.Story;
import com.malmstein.yahnac.settings.SettingsActivity;
import com.malmstein.yahnac.stories.ArticleActivity;

public class Navigator {

    private final HNewsActivity activity;

    public Navigator(HNewsActivity activity) {
        this.activity = activity;
    }

    protected boolean isOnline() {
        NetworkDetector networkDetector = new NetworkDetector(activity);
        return networkDetector.isDataConnectionAvailable();
    }

    public void toExternalBrowser(Uri articleUri) {
        if (isOnline()) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
            browserIntent.setData(articleUri);

            ActivityCompat.startActivity(activity, browserIntent, null);
        }
    }

    public void toInnerBrowser(Story story) {
        if (isOnline()) {
            Intent articleIntent = new Intent(activity, ArticleActivity.class);
            articleIntent.putExtra(ArticleActivity.ARG_STORY, story);

            ActivityCompat.startActivity(activity, articleIntent, null);
        }
    }

    public void toComments(Story story) {
        Intent commentIntent = new Intent(activity, CommentsActivity.class);
        commentIntent.putExtra(CommentsFragment.ARG_STORY, story);

        ActivityCompat.startActivity(activity, commentIntent, null);
    }

    public void toSettings() {
        Intent settingsIntent = new Intent(activity, SettingsActivity.class);
        ActivityCompat.startActivity(activity, settingsIntent, null);
    }
}
