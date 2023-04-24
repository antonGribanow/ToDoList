import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.antongribanow.todolist.Task
import com.antongribanow.todolist.R

class TaskAdapter(
    private val tasks: MutableList<Task>,
    private val onTaskClickListener: (Task) -> Unit,
    private val onTaskLongClickListener: (Task) -> Unit,
    private val onTaskCheckedListener: (Task, Boolean) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskCheckbox: CheckBox = itemView.findViewById(R.id.task_checkbox)
        val taskTitle: TextView = itemView.findViewById(R.id.task_title)
        val taskDescription: TextView = itemView.findViewById(R.id.task_description)
        val taskImage: ImageView = itemView.findViewById(R.id.task_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.taskCheckbox.isChecked = task.isCompleted
        holder.taskTitle.text = task.title
        holder.taskDescription.text = task.description

        if (task.imageBase64 != null) {
            val decodedImage = Base64.decode(task.imageBase64, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.size)
            holder.taskImage.setImageBitmap(bitmap)
        } else {
            holder.taskImage.setImageDrawable(null)
        }

        holder.itemView.setOnClickListener {
            onTaskClickListener(task)
        }

        holder.itemView.setOnLongClickListener {
            onTaskLongClickListener(task)
            true
        }

        holder.taskCheckbox.setOnCheckedChangeListener { _, isChecked ->
            onTaskCheckedListener(task, isChecked)
        }
    }

    override fun getItemCount() = tasks.size
}
