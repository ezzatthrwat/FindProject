package com.example.zizoj.findproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zizoj.findproject.ColorUtiles;
import com.example.zizoj.findproject.Model.Project;
import com.example.zizoj.findproject.ProjectDiscription;
import com.example.zizoj.findproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zizoj on 25/02/2018.
 */

public class ListProjectAdapter extends RecyclerView.Adapter<ListProjectAdapter.HolderView> {

    Context context1;
    List<Project> projectList;
    int toYearColor;


    public ListProjectAdapter(Context context, List<Project> projectList ) {
        this.context1 = context;
        this.projectList = projectList;
    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item,parent,false);

        HolderView view = new HolderView(layout);

        return view;


    }



    @Override
    public void onBindViewHolder(final HolderView holder, int position) {

        holder.projectdiscrip.setText(projectList.get(position).getProjectDescrip());


        holder.projectYear.setText(projectList.get(position).getYear());

        toYearColor = Integer.valueOf(projectList.get(position).getYear());

        int backgroundColorForViewHolder=0;

        if (toYearColor == 2017) {
     backgroundColorForViewHolder = ColorUtiles
            .getViewHolderBackgroundColorFromInstance(context1, 2017);

        }else if (toYearColor == 2016){
             backgroundColorForViewHolder = ColorUtiles
                    .getViewHolderBackgroundColorFromInstance(context1, 2016);

        }else if(toYearColor == 2015){
            backgroundColorForViewHolder = ColorUtiles
                    .getViewHolderBackgroundColorFromInstance(context1, 2015);
        }else if(toYearColor == 2014){
            backgroundColorForViewHolder = ColorUtiles
                    .getViewHolderBackgroundColorFromInstance(context1, 2014);
        }else if(toYearColor == 2013){
            backgroundColorForViewHolder = ColorUtiles
                    .getViewHolderBackgroundColorFromInstance(context1, 2013);
        }

        holder.projectYear.setTextColor(backgroundColorForViewHolder);

    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public void setFilter(List<Project> listPtoject){

        projectList = new ArrayList<>();
        projectList.addAll(listPtoject);
        notifyDataSetChanged();

    }

    public void swapItems(int itemAIndex, int itemBIndex) {
        //make sure to check if dataset is null and if itemA and itemB are valid indexes.
        Project itemA = projectList.get(itemAIndex);
        Project itemB = projectList.get(itemBIndex);
        projectList.set(itemAIndex, itemB);
        projectList.set(itemBIndex, itemA);

        notifyDataSetChanged(); //This will trigger onBindViewHolder method from the adapter.
    }

    class HolderView extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView projectdiscrip , projectYear;

        public HolderView(View itemView) {
            super(itemView);
            projectdiscrip = (TextView)itemView.findViewById(R.id.tv_projectDescrip);
            projectYear = (TextView)itemView.findViewById(R.id.YearTv);

            itemView.setOnClickListener(this);

        }



        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            /*mOnClickListener.onListItemClick(clickedPosition);*/
            Intent intent = new Intent(context1, ProjectDiscription.class);
            intent.putExtra("projectData", projectList.get(clickedPosition));
            context1.startActivity(intent);
        }
    }


}
