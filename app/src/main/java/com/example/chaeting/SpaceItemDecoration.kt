import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) // 현재 아이템의 위치
        val spanCount = 2 // 한 줄에 표시되는 아이템 수

        // 기본적으로 모든 아이템에 아래쪽 간격 적용
        outRect.bottom = spacing

        // 첫 번째 줄의 아이템 처리
        if (position < spanCount) { // 첫 번째 줄에 있는 아이템들 (position 0, 1)
            if (position == 1) {
                // 첫 번째 줄의 두 번째 아이템 (position == 1)의 왼쪽 간격 설정
                outRect.right = spacing
            } else {
                // 첫 번째 줄의 첫 번째 아이템 (position == 0)의 오른쪽 간격 설정
                outRect.right = spacing
            }
        } else {
            // 나머지 아이템 처리 (두 번째 줄부터)
            outRect.right = spacing // 오른쪽 간격
            outRect.left = spacing // 왼쪽 간격
        }
    }
}
