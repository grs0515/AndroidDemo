package com.cmcc.hyapps.KunlunTravel.home.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.cmcc.hyapps.KunlunTravel.R;
import com.cmcc.hyapps.KunlunTravel.base.ImageManager;
import com.cmcc.hyapps.KunlunTravel.home.bean.HomeBannerBean;

public class BannerHolder implements Holder<HomeBannerBean.BannerEntity> {
        private final Context mContent;
        private ImageView image;

        public BannerHolder(Context mContent) {
            this.mContent = mContent;
        }

        @Override
        public View createView(Context context) {
            //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
            View view = LayoutInflater.from(mContent).inflate(R.layout.banner_main, null);
            image = (ImageView) view.findViewById(R.id.main_banner_image);
            return view;
        }

        @Override
        public void UpdateUI(Context context, final int position, HomeBannerBean.BannerEntity data) {
            if (data.getImage_url() != null)
                ImageManager.displayImage(data.getImage_url(), image);
            else
                image.setImageResource(R.drawable.default_loading);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //点击事件
                    //Toast.makeText(view.getContext(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }