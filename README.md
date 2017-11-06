<img src="https://github.com/Kotlin/anko/blob/master/doc/logo.png" alt="Anko logo" height="101" width="220" />

# AnkoAdapterBuilder
Easy implementable recyclerview adapter like pro

## How to implement?

I did not uploaded this library on maven,  
Please add class manually in your project.

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

lateinit var adapterBuilder: AdapterBuilder<User, UserListItemView.UserListItemViewHolder>

override fun onCreate() {

        // Add Initial Data
        adapterBuilder = AdapterBuilder<User, UserListItemView.UserListItemViewHolder>(listOf(User("Harry"), User("Tom")))
        adapterBuilder
                .setOnDataChangedListener { toast("${it.size} Users") }
                .setOnItemClickListener { adapterBuilder.removeData(it) } // Remove Data when Clicked
                .bindData { nameTextView.text = it.name } // Bind Data to View
                .attatchToRecyclerView(LinearLayoutManager(this@MainActivity), recyclerView)
        
        // Add New Data
        adapterBuilder.addData(User("Alice"))
        adapterBuilder.addData(User("Timmy"))
}
```
