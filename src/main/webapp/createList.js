/**
 * 
 */

  function coladd() {
        var table = document.getElementById("answerTxt");
        
        // 行を行末に追加
        var row = table.insertRow(-1);
        
        //td分追加
        var cell1 = row.insertCell(-1);
        var cell2 = row.insertCell(-1);
        
        // セルの内容入力
        cell1.innerHTML = '<div class = "ttl">答え：</div><form class = "data"><input type = "text" class = "addTxt" name="data[]"></form>';
        cell2.innerHTML = '<input type="button" value="削除" id="coladd" onclick="coldel(this)">';
    }
    
	function coldel(obj) {
        // 削除ボタンを押下された行を取得
        tr = obj.parentNode.parentNode;
        // trのインデックスを取得して行を削除する
        tr.parentNode.deleteRow(tr.sectionRowIndex);
     }

     