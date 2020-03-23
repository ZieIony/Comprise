package comprise.test

import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import comprise.app.CompriseActivity
import comprise.material.*
import comprise.view.LayoutSize
import comprise.view.Padding
import comprise.view.Ripple
import comprise.view.Space
import comprise.widget.*

class MainActivity : CompriseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        button = MaterialTextButton(
            name = "TEST BUTTON",
            style = theme.textButtonStyle,
            background = ColorDrawable(resources.getColor(R.color.colorAccent)),
            text = "TEST BUTTON",
            onClick = {
                clickCount++
                button.text = "CLICKS $clickCount"
                button.requestLayout()
                button.requestDraw()
            }
        )

        card = makeCard()

        setContentView(
            Stack(
                width = LayoutSize.MATCH_PARENT,
                height = LayoutSize.MATCH_PARENT,
                name = "main layout",
                children = listOf(
                    Padding(
                        width = LayoutSize.MATCH_PARENT,
                        height = LayoutSize(356.dp),
                        paddingTop = 56.dp,
                        child = Stack(
                            width = LayoutSize.MATCH_PARENT,
                            height = LayoutSize(300.dp),
                            children = listOf(
                                ImageView(
                                    width = LayoutSize.MATCH_PARENT,
                                    height = LayoutSize.MATCH_PARENT,
                                    drawable = ColorDrawable(0xffafafaf.toInt())
                                ),
                                ScrollView(
                                    name = "scroll view",
                                    child = Padding(
                                        width = LayoutSize.MATCH_PARENT,
                                        padding = 16.dp,
                                        child = makeContent()
                                    )
                                )
                            )
                        )
                    ),
                    makeActionBar()
                )
            )
        )
    }

    private val theme = MaterialTheme(this)

    var clickCount = 0

    private lateinit var button: MaterialTextButton

    private lateinit var card: Card

    private fun makeActionBar() = ActionBar(
        name = "action bar",
        style = theme.actionBarStyle,
        icon = IconButton(
            drawable = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
        ),
        title = TextView(
            style = theme.actionBarStyle.textStyle,
            text = "Main activity"
        )
    )

    private fun makeToolbar() = SearchToolbar(
        name = "toolbar",
        style = SearchToolbarStyle(theme.toolbarStyle.textStyle, ColorDrawable(Color.WHITE)),
        icon = resources.getDrawable(R.drawable.ic_search_black_24dp),
        hint = "Search"
    )

    private fun makeContent() = Column(
        children = listOf(
            makeToolbar(),
            Space(16.dp),
            card,
            Space(16.dp),
            button,
            Space(16.dp),
            MaterialButton(
                name = "TEST BUTTON 2",
                style = theme.buttonStyle,
                width = LayoutSize.MATCH_PARENT,
                content = Padding(
                    padding = 8.dp,
                    child = Row(
                        width = LayoutSize.MATCH_PARENT,
                        height = LayoutSize.WRAP_CONTENT,
                        children = listOf(
                            MaterialIcon(drawable = resources.getDrawable(R.drawable.ic_launcher_foreground)),
                            TextView(
                                style = theme.textButtonStyle.textStyle,
                                text = "TEST BUTTON 2"
                            )
                        )
                    )
                )
            ),
            Space(16.dp),
            Button(
                name = "TEST BUTTON 3",
                style = theme.buttonStyle,
                width = LayoutSize(56.dp),
                height = LayoutSize(56.dp),
                content = TextView(
                    style = theme.textButtonStyle.textStyle,
                    text = "TEST BUTTON 3"
                )
            )
        )
    )

    private fun makeCard(): Card {
        val card = Card(
            width = LayoutSize.MATCH_PARENT,
            height = LayoutSize(200),
            content = Stack(
                children = listOf(
                    ImageView(
                        width = LayoutSize.MATCH_PARENT,
                        height = LayoutSize.MATCH_PARENT,
                        drawable = ColorDrawable(0xff7fffff.toInt())
                    ),
                    Ripple(
                        width = LayoutSize.MATCH_PARENT,
                        height = LayoutSize.MATCH_PARENT,
                        child = Column(
                            children = listOf(
                                ImageView(
                                    drawable = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
                                ),
                                TextView(
                                    text = "test",
                                    textSize = resources.getDimension(R.dimen.comprise_textSize)
                                ),
                                ImageView(
                                    drawable = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
                                )
                            )
                        )
                    )
                )
            )
        )

        val animator = ValueAnimator.ofFloat(200.0f, 600.0f)
        animator.repeatMode = ValueAnimator.REVERSE
        animator.duration = 3000
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.repeatCount = ValueAnimator.INFINITE
        animator.addUpdateListener {
            card.desiredHeight = LayoutSize((it.animatedValue as Float).toInt())
            card.requestLayout()
            card.requestDraw()
        }
        //animator.start()

        return card
    }
}

