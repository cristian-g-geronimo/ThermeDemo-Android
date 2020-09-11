package ro.trm.demo.tools;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gxorg.god.tools.PagedModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kotlinx.coroutines.Dispatchers;


/**
 * Created by cristig on 22/05/17.
 */
public class RecyclerBaseAdapter<T, V extends BaseListItemView<T>> extends
        PagingDataAdapter<T, RecyclerBaseAdapter.ViewHolder> {
    private final Class<?> mClazz;
    private final boolean mPaging;
    private Object mTag;
    private Class<?> mHeaderClass;
    protected List<T> mObjectList = new ArrayList<>();

    private ViewGroup.LayoutParams mLayoutParams;

    private BaseListItemView.GenericListener mGenericListener;
    private Object mEmptyListener;
    protected BaseListItemView.BaseListener<T> mListener;


    private RecyclerBaseAdapter(Class<?> header,
                                Class<?> clazz,
                                boolean paging,
                                ViewGroup.LayoutParams params,
                                BaseListItemView.BaseListener<T> listener,
                                BaseListItemView.GenericListener genericListener,
                                Object emptyListener,
                                Object tag) {
        super(new DiffUtil.ItemCallback<T>() {
            @Override
            public boolean areItemsTheSame(@NonNull T oldItem, @NonNull T newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull T oldItem, @NonNull T newItem) {
                if (oldItem instanceof PagedModel) {
                    return ((PagedModel) oldItem).areContentsTheSame(newItem);
                }
                return Objects.equals(oldItem, newItem);
            }
        }, Dispatchers.getMain(), Dispatchers.getDefault());
        mHeaderClass = header;
        mClazz = clazz;
        mPaging = paging;
        mLayoutParams = params;
        mListener = listener;
        mGenericListener = genericListener;
        mEmptyListener = emptyListener;
        mTag = tag;
    }


    @Override
    public int getItemCount() {
        int size = mObjectList.size();
        return mPaging ? super.getItemCount() : size;
    }

    public void setObjectList(List<T> objectList) {
        mObjectList.clear();
        if (objectList != null) {
            mObjectList.addAll(objectList);
        }
        notifyDataSetChanged();
    }

    public void addObjectList(List<T> objectList) {
        mObjectList.addAll(objectList);
        notifyDataSetChanged();
    }

    public void insertFirst(T object) {
        mObjectList.add(0, object);
        notifyDataSetChanged();
    }

    public void removeObject(T object) {
        int index = mObjectList.indexOf(object);
        mObjectList.remove(object);
        notifyItemRemoved(index);
    }

    public void setLayoutParams(ViewGroup.LayoutParams params) {
        mLayoutParams = params;
    }

    public void setTag(Object mTag) {
        this.mTag = mTag;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        V view = null;
        try {
            //noinspection unchecked
            view = (V) SubclassingController.newInstance(mClazz, parent.getContext());
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

        if (view == null) {
            throw new IllegalArgumentException("itemView may not be null");
        }

        if (mLayoutParams != null) {
            view.setLayoutParams(mLayoutParams);
        } else {
            view.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        view.setListener(mListener);
        view.setGenericListener(mGenericListener);
        view.setEmptyListener(mEmptyListener);
        view.setTag(mTag);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        T object = mPaging ? getItem(position) : mObjectList.get(position);
        if (object == null) {
            return;
        }
        //noinspection unchecked
        ((Assignable<T>) holder.itemView).setObject(object);
    }

    public List<T> getItems() {
        ArrayList<T> list = new ArrayList<>();
        if (mObjectList != null) {
            list.addAll(mObjectList);
        }
        return list;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void objectChanged(T item) {
        notifyItemChanged(mObjectList.indexOf(item));
    }

    /**
     * View holder pattern.
     */
    protected static class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * holder
         *
         * @param itemView view to hold.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface Assignable<T> {

        void setObject(T object);

    }

    public interface LoadMoreListener {
        void onLoadMoreRequested(int page);
    }

    public static class Builder<T, V extends BaseListItemView<T>> {

        private BaseListItemView.GenericListener mGenericListener;
        private Object mEmptyListener;
        private BaseListItemView.BaseListener<T> mListener;
        private Class<?> mClazz;
        private Object mTag;
        private ViewGroup.LayoutParams mLayoutParams;
        private boolean mPaging;

        public Builder<T, V> setClass(Class<?> clazz) {
            mClazz = clazz;
            return this;
        }

        public Builder<T, V> setGenericListener(BaseListItemView.GenericListener listener) {
            mGenericListener = listener;
            return this;
        }

        public Builder<T, V> setBaseListener(BaseListItemView.BaseListener<T> listener) {
            mListener = listener;
            return this;
        }

        public Builder<T, V> setEmptyListener(Object listener) {
            mEmptyListener = listener;
            return this;
        }

        public Builder<T, V> setPaging(boolean paging) {
            mPaging = paging;
            return this;
        }

        public Builder<T, V> setTag(Object tag) {
            mTag = tag;
            return this;
        }

        public Builder<T, V> setLayoutParams(ViewGroup.LayoutParams params) {
            mLayoutParams = params;
            return this;
        }

        public RecyclerBaseAdapter<T, V> build() {
            if (mClazz == null) {
                throw new IllegalArgumentException("please provide class");
            }
            return new RecyclerBaseAdapter<>(null,
                    mClazz,
                    mPaging,
                    mLayoutParams,
                    mListener,
                    mGenericListener,
                    mEmptyListener,
                    mTag);
        }
    }
}