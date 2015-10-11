

public class XYZActivity extends AppCompatActivity {

    private View promptView;
    private AlertDialog postDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                // show alert dialog
                LayoutInflater layoutInflater = LayoutInflater.from(ctx);
                promptView = layoutInflater.inflate(R.layout.dialog_perform_signout, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
                alertDialogBuilder.setView(promptView);
                alertDialogBuilder
                        .setCancelable(true)
                        .setPositiveButton(R.string.perform_notification_delete,null)
                        .setNegativeButton(R.string.button_cancel,null);

                // create an alert dialog
                postDialog = alertDialogBuilder.create();
                postDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        Button POSButton = postDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        POSButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // delete all chats

                                postDialog.dismiss();

                            }
                        });

                        Button NEGButton = postDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                        NEGButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Dismiss when everything is OK
                                postDialog.dismiss();
                            }
                        });
                    }
                });
                postDialog.show();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}

/**
 * Structure to hold individual notification
 */
class xyzContainer{
    String text;
    String time;
}

/**
 * Adapter for recyclerview
 */
class xyzAdapter extends RecyclerView.Adapter<xyzAdapter.ViewHolder>{
    private List<xyzContainer> mDataset;
    private Context ctx;

    public NotificationAdapter(List<xyzContainer> notifications,Context ctx){
        this.mDataset = notifications;
        this.ctx = ctx;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtText;
        public TextView txtTime;

        public ViewHolder(View v){
            super(v);
            txtText = (TextView) v.findViewById(R.id.txtText);
            txtTime = (TextView) v.findViewById(R.id.txtTime);
        }
    }

    public void add(int position,xyzContainer xyz){
        mDataset.add(position, xyz);
        notifyItemInserted(position);
    }

    public void remove(xyzContainer xyz){
        int position = mDataset.indexOf(xyz);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_notification,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    /*
     * This binds the data with the view,all formatting logic goes here
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final xyzContainer xyz = mDataset.get(position);
        holder.txtText.setText(mDataset.get(position).text);
        holder.txtTime.setText(mDataset.get(position).time);
    }
}
