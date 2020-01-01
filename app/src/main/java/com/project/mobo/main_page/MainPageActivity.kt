package com.project.mobo.main_page

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.main_page.choice_movie.ChoiceMovieAdapter
import com.project.mobo.main_page.choice_movie.ChoiceMovieRepository
import com.project.mobo.main_page.choice_time.DataVerticalAdapter
import com.project.mobo.main_page.choice_time.data.MainData
import com.project.mobo.main_page.choice_time.data.MainReserveDate
import com.project.mobo.main_page.choice_time.innerData.ChoiceDateAdapter
import com.project.mobo.main_page.choice_time.innerData.ChoiceDateRepository
import com.project.mobo.main_page.top_three_viewPager.MovieData
import com.project.mobo.main_page.top_three_viewPager.MoviePagerAdapter
import com.project.mobo.movie_selection.display.MovieSelectionActivity
import com.project.mobo.mypage.MyPage_new
import com.project.mobo.time_choice.TimeChoiceActivity
import kotlinx.android.synthetic.main.activity_main_page.*
import kotlinx.android.synthetic.main.fragment_main.*
import android.net.Uri
import com.project.mobo.R
import com.project.mobo.dialog.FailDialog
import com.project.mobo.dialog.SuccessDialog


class MainPageActivity : AppCompatActivity() {
    private lateinit var rvmovieChoice: RecyclerView
    private lateinit var movieChoiceAdapter: ChoiceMovieAdapter
    private val movieRepository= ChoiceMovieRepository()

    private lateinit var rvdateChoice: RecyclerView
    private lateinit var dateChoiceAdapter: ChoiceDateAdapter
    private val dateRepository=
        ChoiceDateRepository()

    private lateinit var mainData: MainData

    private lateinit var verticalAdapter : DataVerticalAdapter

    var state = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        //여기서부터 지울 영역
        button_check.setOnClickListener {
            if(state == 0){
                val failDialog = FailDialog()
                failDialog.show(supportFragmentManager, "fail")
            }else{
                val successDialog = SuccessDialog()
                successDialog.show(supportFragmentManager, "success")
            }
        }
        ///여기까지
        movePage() //intent

        dummy()

        topThree() // 뷰페이저
        choiceMovie() // 선택한 영화 창
        choiceDate() // 선택한 날짜 창

        //setOnClickListener()
    }

    private fun dummy(){
        mainData = MainData(ArrayList(), ArrayList(), ArrayList())
        mainData.reserveDate.add(MainReserveDate("14", arrayListOf<String>("12", "13", "14", "15")))
        mainData.reserveDate.add(MainReserveDate("15", arrayListOf<String>("12", "13")))
        mainData.reserveDate.add(MainReserveDate("16", arrayListOf<String>("12", "13", "14")))
        mainData.reserveDate.add(MainReserveDate("17", arrayListOf<String>("12", "13", "14", "15", "16")))
        mainData.reserveDate.add(MainReserveDate("18", arrayListOf<String>("12", "13", "14")))
        mainData.reserveDate.add(MainReserveDate("19", arrayListOf<String>("12")))
        mainData.reserveDate.add(MainReserveDate("20", arrayListOf<String>("12", "13", "14", "15")))

    }


    private fun movePage() {
        //마이페이지로 이동
        btnMyPG.setOnClickListener(){

            val intent1 = Intent(this, MyPage_new::class.java)
            startActivity(intent1)
        }
        //시간수정창으로 이동
        btnTimeChanging.setOnClickListener(){
            val intent2 = Intent(this, TimeChoiceActivity::class.java)
            startActivity(intent2)
        }

        //영화수정창으로 이동
        btnMovieChanging.setOnClickListener(){
            val intent3 = Intent(this, MovieSelectionActivity::class.java)
            startActivity(intent3)
        }

        //영화/시간 선택 시작
        btnMovieChoiceStart.setOnClickListener(){
            val intent4 = Intent(this, MovieSelectionActivity::class.java)
            startActivity(intent4)
        }
    }

    private fun topThree() {

        val adapter = MoviePagerAdapter(movieList)
        vpMain.setClipToPadding(false)
        vpMain.setPadding(34,0,34,0)

        vpMain.pageMargin=8
        vpMain.adapter = adapter

        //btnMainFirstPlay.setOnClickListener(){
        //    val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/li4jOV5j7SI"))
        //startActivity(i)
        //}
    }

    companion object {
        val movieList = arrayListOf(
            MovieData(
                "https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/6atO/image/nDpOWQrYaI2MQ514GXPpCOFkJUU",
                "엑시트"
                //"4.5"
                //"1h 40m"i
            ),
            MovieData(
                "https://t1.daumcdn.net/cfile/tistory/236A5E4D52D6B15B03",
                "어바웃 타임"
                //"4"
                //"1h 40m"
            ),
            MovieData(
                "https://cools.co/wp-content/uploads/2019/12/80218028_3472865946120662_576660455397785600_n.jpg",
                "천문"
                //"3.5"
                //"1h 40m"
            )
        )
    }

    private fun choiceMovie(){
        rvmovieChoice=findViewById(R.id.rvMovieChoice)
        movieChoiceAdapter= ChoiceMovieAdapter(this)

        rvmovieChoice.adapter=movieChoiceAdapter

        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.HORIZONTAL
        rvmovieChoice.setLayoutManager(llm)
        rvmovieChoice.setAdapter(movieChoiceAdapter)

        movieChoiceAdapter.data=movieRepository.getRepoList()


        movieChoiceAdapter.notifyDataSetChanged()
    }


    private fun choiceDate(){
        //지금부터 얘는 큰 거
        rvdateChoice=findViewById(R.id.rvMainDate)

        //TODO : 서버 통신 이후, 반드시 reserveDate만 넘겨줄 것.
        verticalAdapter = DataVerticalAdapter(this, mainData.reserveDate)
//        dateChoiceAdapter= choiceDateAdapter(this)

        rvdateChoice.layoutManager = LinearLayoutManager(this)
        rvdateChoice.adapter=verticalAdapter

//        val llm = LinearLayoutManager(this)
//        llm.orientation = LinearLayoutManager.HORIZONTAL
//        rvdateChoice.setLayoutManager(llm)
//        rvdateChoice.setAdapter(dateChoiceAdapter)
//
//
//        dateChoiceAdapter.data=dateRepository.getRepoList()
//
//        dateChoiceAdapter.notifyDataSetChanged()

    }

    private fun setOnClickListener(){
        btnMainFirstPlay.setOnClickListener(){
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/li4jOV5j7SI"))
            startActivity(i)
        }
    }

}
