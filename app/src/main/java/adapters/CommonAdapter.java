package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import a.a.mobileinfo.R;

/**
 * Created by Arpit on 24/04/15.
 */
public class CommonAdapter extends ArrayAdapter<String> {

    protected Context mContext;
    protected String[] mDesc;
    protected String[] mproperty;
    protected int i = 0;
    protected String mType;

    public CommonAdapter(Context context, String[] desc, String[] property, String type) {
        super(context, R.layout.list_item, desc);
        mContext = context;
        mDesc = desc;
        mproperty = property;
        mType = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.list_item, null);
            holder = new ViewHolder();

            holder.desc = (TextView) convertView.findViewById(R.id.desc);
            holder.property = (TextView) convertView.findViewById(R.id.property);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.desc.setText(mDesc[position]);
        holder.property.setText(mproperty[position]);

        return convertView;
    }

    public static class ViewHolder {
        TextView desc;
        TextView property;
    }
}
