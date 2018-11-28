package com.chen.test.activity.gallery;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chen.common.util.UIUtils;
import com.chen.test.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenxianglin on 2017/12/18.
 * Class note:
 */

@SuppressLint("NewApi")
public class GalleryActivity extends Activity {
    private static final String TAG = "GalleryActivity";
    public static final float BITMAP_SCALE = 0.4f;

    @BindView(R.id.gallery)
    RecyclerView mGallery;
    @BindView(R.id.back)
    TextView mBack;

    private GalleryAdapter mAdapter;
    private LinearLayoutManager mManager;
    private PagerSnapHelper mSnapHelper;
    private int pageMargin = 20;//页边距
    private int mPageVisibleWidth = 20;//可视宽度
    private int itemWidth;
    private int itemCount;
    private int screenWidth;
    private float mAnimFactor = 0.2f;

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, GalleryActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        initView();
        getData();
    }

    private void initView() {
        screenWidth = UIUtils.getScreenWidth(this);
        mAdapter = new GalleryAdapter(this);
        mManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mSnapHelper = new PagerSnapHelper();
        mSnapHelper.attachToRecyclerView(mGallery);
        mGallery.addItemDecoration(new GalleryItemDecoration());
        mGallery.setLayoutManager(mManager);
        mGallery.setAdapter(mAdapter);
        mGallery.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //计算位置
                View v = mSnapHelper.findSnapView(mManager);
                int position = recyclerView.getChildAdapterPosition(v);
                Log.d(TAG, "=dx=" + dx + "=position=" + position);


                int left = v.getLeft();
                int margin = dpToPx(pageMargin);
                int visible = dpToPx(mPageVisibleWidth);
                Log.d(TAG, "=left=" + left);
                Log.d(TAG, "=screenWidth=" + screenWidth + "=item=" + itemWidth);
                Log.d(TAG, "pageMargin=" + margin + "=visible=" + visible);

                float rate;
                float leftMargin = visible + 2f * margin;//240,不动时，距左边距
                float half = screenWidth / 2f;
                float changeWidth = half - margin;//640,改变position的宽度
                //当左移，左边距240+剩下显示边距(960-640=320)时，滑动结束距离，此时前后移动距离相等，导致position的改变
                float scrollWidth = itemWidth - changeWidth + leftMargin;//560,滑动改变的距离,960-640=320+240

                //left从（正240-->零-->负320/正800<position改变-->前后移动距离相等导致>-->正240）
//                if (left > 0) {
//                    if (left > leftMargin) {
//                        rate = (left - leftMargin) / scrollWidth;
//                        rate *= 0.5f;
//                    } else {
//                        rate = (left + changeWidth / 2) / scrollWidth;
//                        rate = rate * 0.5f + 0.5f;
//                    }
//                } else {
//                    rate = (left + changeWidth / 2) / scrollWidth;
//                    rate = rate * 0.5f + 0.5f;
//                }


                //演化
                if (left > leftMargin) {
                    rate = (left - leftMargin) / scrollWidth;
                    rate *= 0.5f;
                } else {
                    rate = (left + itemWidth - changeWidth) / scrollWidth;
                    rate = rate * 0.5f + 0.5f;
                }
                Log.d(TAG, "rate=" + rate);


                // 设置动画变化
                setAnimation(recyclerView, position, rate);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    private void setAnimation(RecyclerView recyclerView, int position, float percent) {
        View mCurView = recyclerView.getLayoutManager().findViewByPosition(position);       // 中间页
        View mRightView = recyclerView.getLayoutManager().findViewByPosition(position + 1); // 左边页
        View mLeftView = recyclerView.getLayoutManager().findViewByPosition(position - 1);  // 右边页

        if (percent <= 0.5) {
            if (mLeftView != null) {
                // 变大
                mLeftView.setScaleX((1 - mAnimFactor) + percent * mAnimFactor);
                mLeftView.setScaleY((1 - mAnimFactor) + percent * mAnimFactor);
            }
            if (mCurView != null) {
                // 变小
                mCurView.setScaleX(1 - percent * mAnimFactor);
                mCurView.setScaleY(1 - percent * mAnimFactor);
            }
            if (mRightView != null) {
                // 变大
                mRightView.setScaleX((1 - mAnimFactor) + percent * mAnimFactor);
                mRightView.setScaleY((1 - mAnimFactor) + percent * mAnimFactor);
            }
        } else {
            if (mLeftView != null) {
                mLeftView.setScaleX(1 - percent * mAnimFactor);
                mLeftView.setScaleY(1 - percent * mAnimFactor);
            }
            if (mCurView != null) {
                mCurView.setScaleX((1 - mAnimFactor) + percent * mAnimFactor);
                mCurView.setScaleY((1 - mAnimFactor) + percent * mAnimFactor);
            }
            if (mRightView != null) {
                mRightView.setScaleX(1 - percent * mAnimFactor);
                mRightView.setScaleY(1 - percent * mAnimFactor);
            }
        }
    }

    private void getData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("position=" + i);
        }
        mAdapter.setData(list);
    }

    @OnClick({R.id.back})
    public void onItemClick(View v) {
        if (v.getId() == R.id.back) {
            finish();
        }
    }

    /**
     * 获取位置
     *
     * @param mConsumeX      实际消耗距离
     * @param shouldConsumeX 移动一页理论消耗距离
     */
    private int getPosition(int mConsumeX, int shouldConsumeX) {
        float offset = (float) mConsumeX / (float) shouldConsumeX;
        int position = Math.round(offset);        // 四舍五入获取位置
        return position;
    }

    public class GalleryItemDecoration extends RecyclerView.ItemDecoration {


        int itemX = 0;

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int position = parent.getChildLayoutPosition(view);
            itemCount = mAdapter.getItemCount();

            // 动态修改页面的宽度
            itemWidth = screenWidth - dpToPx(4 * pageMargin + 2 * mPageVisibleWidth);
            // 一页理论消耗距离
            itemX = itemWidth + dpToPx(2 * pageMargin);

            // 第0页和最后一页没有左页面和右页面，让他们保持左边距和右边距和其他项一样
            int leftMargin = position == 0 ? dpToPx(mPageVisibleWidth + 2 * pageMargin) : dpToPx(pageMargin);
            int rightMargin = position == itemCount - 1 ? dpToPx(mPageVisibleWidth + 2 * pageMargin) : dpToPx(pageMargin);
            int topMargin = dpToPx(pageMargin);
            int bottomMargin = topMargin;

            // 设置参数
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
            lp.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
            lp.width = itemWidth;
            view.setLayoutParams(lp);
        }
    }

    public int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density + 0.5f);
    }

    public Bitmap blurBitmap(Context context, Bitmap image, float blurRadius) {
        // 计算图片缩小后的长宽
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        // 将缩小后的图片做为预渲染的图片
        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        // 创建一张渲染后的输出图片
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        // 创建RenderScript内核对象
        RenderScript rs = RenderScript.create(context);
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

        // 设置渲染的模糊程度, 25f是最大模糊度
        blurScript.setRadius(blurRadius);
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);

        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }
}
