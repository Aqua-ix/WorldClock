package com.aqua_ix.worldclock

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class TimeZoneSelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_zone_select)

        // 最初に「キャンセルされた」結果を返すように設定しておくと、
        // 戻るボタンをタップした時などに対応できる
        setResult(Activity.RESULT_CANCELED)

        // タイムゾーンリストをレイアウトから探す
        val list = findViewById<ListView>(R.id.clockList)
        // タイムゾーンリスト表示用のアダプターを作る
        val adapter = TimeZoneAdapter(this)

        // リストにアダプターをセットする
        list.adapter = adapter

        // リストタップ時の動作
        // Kotlinでは、ラムダ式で使用しない引数を_で省略できる
        list.setOnItemClickListener {_, _, position, _ ->
            // タップした場所のタイムゾーンをリストから得る
            val timeZone = adapter.getItem(position)

            // 遷移元の画面に返す結果
            val result = Intent()
            // ユーザーがタップしたタイムゾーンを設定する
            result.putExtra("timeZone", timeZone)
            // 「OK」の結果を返す
            setResult(Activity.RESULT_OK, result)

            // この画面を閉じる
            finish()
        }
    }
}
