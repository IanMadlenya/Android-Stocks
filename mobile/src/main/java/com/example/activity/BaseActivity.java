package com.example.activity;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.example.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import eu.inloop.viewmodel.base.ViewModelBaseEmptyActivity;


public abstract class BaseActivity extends ViewModelBaseEmptyActivity
{
	public static final int INDICATOR_TYPE_NONE = 0;
	public static final int INDICATOR_TYPE_BACK = 1;
	public static final int INDICATOR_TYPE_CLOSE = 2;


	@Retention(RetentionPolicy.SOURCE)
	@IntDef({INDICATOR_TYPE_NONE, INDICATOR_TYPE_BACK, INDICATOR_TYPE_CLOSE})
	public @interface IndicatorType {}


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}


	@Override
	public void onStart()
	{
		super.onStart();
	}


	@Override
	public void onResume()
	{
		super.onResume();
	}


	@Override
	public void onPause()
	{
		super.onPause();
	}


	@Override
	public void onStop()
	{
		super.onStop();
	}


	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}


	@Nullable
	public ActionBar setupActionBar(@IndicatorType int indicatorType)
	{
		return setupActionBar(indicatorType, null, null);
	}


	@Nullable
	public ActionBar setupActionBar(@IndicatorType int indicatorType, @Nullable CharSequence title)
	{
		return setupActionBar(indicatorType, title, null);
	}


	@Nullable
	public ActionBar setupActionBar(@IndicatorType int indicatorType, @Nullable CharSequence title, @Nullable Toolbar toolbar)
	{
		if(toolbar == null)
		{
			toolbar = (Toolbar) findViewById(R.id.toolbar);
		}
		setSupportActionBar(toolbar);

		ActionBar actionBar = getSupportActionBar();
		if(actionBar != null)
		{
			actionBar.setDisplayUseLogoEnabled(false);
			actionBar.setDisplayShowTitleEnabled(true);
			actionBar.setDisplayShowHomeEnabled(true);

			if(indicatorType == INDICATOR_TYPE_NONE)
			{
				actionBar.setDisplayHomeAsUpEnabled(false);
				actionBar.setHomeButtonEnabled(false);
			}
			else if(indicatorType == INDICATOR_TYPE_BACK)
			{
				actionBar.setDisplayHomeAsUpEnabled(true);
				actionBar.setHomeButtonEnabled(true);
			}
			else if(indicatorType == INDICATOR_TYPE_CLOSE)
			{
				actionBar.setDisplayHomeAsUpEnabled(true);
				actionBar.setHomeButtonEnabled(true);
				actionBar.setHomeAsUpIndicator(R.drawable.ic_close);
			}

			if(title != null)
			{
				actionBar.setTitle(title);
			}
		}
		return actionBar;
	}


	public void replaceFragment(Fragment fragment)
	{
		replaceFragment(fragment, true, false);
	}


	public void replaceFragment(Fragment fragment, boolean addToBackStack, boolean allowStateLoss)
	{
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragment);

		if(addToBackStack)
		{
			transaction.addToBackStack(fragment.getClass().getSimpleName());
		}

		if(allowStateLoss)
		{
			transaction.commitAllowingStateLoss();
		}
		else
		{
			transaction.commit();
		}
	}
}
