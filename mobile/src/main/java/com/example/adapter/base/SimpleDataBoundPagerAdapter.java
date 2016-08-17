package com.example.adapter.base;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;

import com.example.BR;
import com.example.ui.BaseView;

import java.lang.ref.WeakReference;


abstract public class SimpleDataBoundPagerAdapter<T extends ViewDataBinding> extends BaseDataBoundPagerAdapter<T>
{
	@LayoutRes private int mLayoutId;
	private BaseView mView;
	private ObservableArrayList<?> mItems;
	private OnListChangedCallback<T> mOnListChangedCallback;


	public SimpleDataBoundPagerAdapter(@LayoutRes int layoutId, BaseView view, ObservableArrayList<?> items)
	{
		mLayoutId = layoutId;
		mView = view;
		mItems = items;

		mOnListChangedCallback = new OnListChangedCallback<>(this);
		mItems.addOnListChangedCallback(mOnListChangedCallback);
	}


	@Override
	protected void bindItem(T binding, int position)
	{
		binding.setVariable(BR.view, mView);
		binding.setVariable(BR.data, mItems.get(position));
	}


	@Override
	public int getItemLayoutId(int position)
	{
		return mLayoutId;
	}


	@Override
	public int getCount()
	{
		return mItems.size();
	}


	private static class OnListChangedCallback<T extends ViewDataBinding> extends ObservableList.OnListChangedCallback<ObservableList<?>>
	{
		private final WeakReference<SimpleDataBoundPagerAdapter<T>> mAdapter;


		public OnListChangedCallback(SimpleDataBoundPagerAdapter<T> adapter)
		{
			mAdapter = new WeakReference<>(adapter);
		}


		@Override
		public void onChanged(ObservableList<?> sender)
		{
			onUpdate();
		}


		@Override
		public void onItemRangeChanged(ObservableList<?> sender, int positionStart, int itemCount)
		{
			onUpdate();
		}


		@Override
		public void onItemRangeInserted(ObservableList<?> sender, int positionStart, int itemCount)
		{
			onUpdate();
		}


		@Override
		public void onItemRangeMoved(ObservableList<?> sender, int fromPosition, int toPosition, int itemCount)
		{
			onUpdate();
		}


		@Override
		public void onItemRangeRemoved(ObservableList<?> sender, int positionStart, int itemCount)
		{
			onUpdate();
		}


		private void onUpdate()
		{
			if(mAdapter.get() != null)
			{
				mAdapter.get().notifyDataSetChanged();
			}
		}
	}
}
