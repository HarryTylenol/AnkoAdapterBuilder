<img src="https://github.com/Kotlin/anko/blob/master/doc/logo.png" alt="Anko logo" height="101" width="220" />

# AnkoAdapterBuilder
Easy implementable recyclerview adapter like pro

## How to implement?

I did not uploaded this library on maven,  
Please add manually class in your project.

## How to use?

### Make Item View which inherit AnkoListItemView & ViewHolder.
You should set your viewholder as view's tag

```kotlin
class UserListItemView(context: Context) : AnkoListItemView<UserListItemView.UserListItemViewHolder>(context) {

    // Set ViewHolder
    class UserListItemViewHolder(view: View, var nameTextView: TextView) : RecyclerView.ViewHolder(view)

    lateinit var nameTextView: TextView

    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        frameLayout {
            lparams(matchParent)
            
            padding = dip(24)
            nameTextView = textView()
            
            // Set View Holder as Tag
            tag = UserListItemViewHolder(this, nameTextView)
        }
    }

}
```

### Add AnkoAdapterBuilder


```kotlin
data class User(var name : String)

AnkoAdapterBuilder<User, UserListItemView.UserListItemViewHolder>()
      .setData(arrayListOf(User("Harry"), User("Tom"), User("Jane"))) // Set List Data
      .setView(UserListItemView(this)) // Set View
      .setOnItemClickListener { user -> toast("User${adapterPosition} : ${user.name} Selected") } // Click Listener
      .bindData { user -> nameTextView.text = user.name } // Bind data to view
      .attatchToRecyclerView(recyclerView) // to Recyclerview
```
