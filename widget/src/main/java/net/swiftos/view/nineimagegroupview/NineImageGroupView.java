package net.swiftos.view.nineimagegroupview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lazh on 2016/3/17.
 */
public class NineImageGroupView extends FrameLayout {
    private List<String> imageUrls; //图片地址
    private List<ImageView> imageViews; //图片控件  用来缓存ImageView

    private int mWidth = 0 ; //NineImageGroupView 控件的宽度
    private int singleImgMaxHeight = 0 ;
    private int gridSize = 0; //多图的宽高
    private int intervalGridSize = 5; //间距

    int row = 0;
    int column = 0;

    private LayoutParams singleLayoutParam ;
    private LayoutParams layoutParam ;

    private NineImageAdapter adapter ;

    public NineImageGroupView(Context context) {
        super(context);
        init();
    }

    public NineImageGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NineImageGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        imageViews = new ArrayList<>(); //初始化9个
        intervalGridSize = (int) (3 * getResources().getDisplayMetrics().density + 0.5f) ;//默认为3dp
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec) ;
        int totalWidth = width - getPaddingLeft() - getPaddingRight() ;
        if(mWidth == 0){
            mWidth = width;
            singleImgMaxHeight = mWidth * 3 /4 ;
            gridSize = totalWidth /3 ;

            singleLayoutParam = new LayoutParams(mWidth, LayoutParams.WRAP_CONTENT);
            layoutParam = new LayoutParams(gridSize,gridSize);
            setDatas(imageUrls); //触发测量后 需要回调setDatas 设置View参数
        }
        if(imageUrls.size()>1){
            int mHeight = gridSize*row + intervalGridSize*(row -1) + getPaddingTop()+getPaddingBottom() ;
            setMeasuredDimension(width , mHeight);
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if(imageUrls.size() > 1){ //需要自己测量宽高
            for(int i=0;i<imageUrls.size();i++){

                ImageView child = (ImageView) getChildAt(i);
                int l = getPaddingLeft() + (gridSize+intervalGridSize)*(i%3);
                int t = getPaddingTop() +(gridSize+intervalGridSize)*(i/3);
                int r = l + gridSize ;
                int b = t + gridSize ;
                child.layout(l,t,r,b);
            }
        }else{
            super.onLayout(changed, left, top, right, bottom);
        }

    }
    public void setDatas(List<String> imageUrlDatas){
        this.removeAllViews();
        this.imageUrls = imageUrlDatas ;
        if(mWidth == 0) { //如果宽度为0则重新去测量控件的宽高 需要addView触发
            addView(new View(getContext()));
            return ;
        }
        setRowAndColumn();
        if(imageUrlDatas == null || imageUrlDatas.isEmpty()) return ;
        for(int i=0;i<imageUrlDatas.size();i++){
            addView(getImageView(i));
        }
    }

    private void setRowAndColumn() {
        if(imageUrls.size() == 4){
            row = 2 ;
            column = 2 ;
        }else{
            column = 3;
            row =  (imageUrls.size()/3) + (imageUrls.size()%3==0?0:1);
        }
    }

    private ImageView getImageView(int position) {
        if(adapter == null) throw new RuntimeException("Please set NineImageAdapter , the NineImageAdapter can not be null !");
        ImageView iv = null;
        if(imageViews.size() > position){
            iv = imageViews.get(position);
        }else{
            iv = adapter.initImageView(getContext());
            imageViews.add(iv);
        }
        if(imageUrls.size() == 1){ //设置只有一张图片的参数
            iv.setLayoutParams(singleLayoutParam);
            iv.setMaxHeight(singleImgMaxHeight);
            iv.setAdjustViewBounds(true);
            iv.setScaleType(ImageView.ScaleType.FIT_START);
        }else{ //设置有多重图片的参数
            iv.setLayoutParams(layoutParam);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        adapter.displayImageView(getContext(),iv,position,imageUrls.get(position));
        return iv ;
    }

    public NineImageAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(NineImageAdapter adapter) {
        this.adapter = adapter;
    }
}
