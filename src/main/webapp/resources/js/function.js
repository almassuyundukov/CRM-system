function deleteStudents(){
    var checkedCheckboxs = document.querySelectorAll('input[name=idStudent]:checked')
    if(checkedCheckboxs.length == 0){
        alert("Выберите хотя бы одного студента!!!");
        return;
    }

    // 1-2-5-7 - string
    var ids = ""
    for(var i = 0; i < checkedCheckboxs.length; i++){
        ids = ids + checkedCheckboxs[i].value + " ";
    }

    document.getElementById("deleteHidden").value = ids;
    document.getElementById('deleteForm').submit();

}


function modifyStudents(){
    var checkedCheckboxs = document.querySelectorAll('input[name=idStudent]:checked')
    if(checkedCheckboxs.length == 0){
        alert("Выберите студента!!!");
        return;
    }

    if(checkedCheckboxs.length > 1){
        alert("Выберите только одного студента!!!");
        return;
    }
    // 1 - string
    var id = checkedCheckboxs[0].value;
    document.getElementById("modifyHidden").value = id;
    document.getElementById('modifyForm').submit();

}

function selectDisciplines(){
    var checkedCheckboxs = document.querySelectorAll('input[name=disciplinesId]:checked')
    if(checkedCheckboxs.length == 0){
        alert("Выберите хотя бы одну дисциплину!!!");
        return;
    }

    // 1-2-5-7 - string
    var ids = ""
    for(var i = 0; i < checkedCheckboxs.length; i++){
        ids = ids + checkedCheckboxs[i].value + " ";
    }

    document.getElementById("selectHidden").value = ids;
    document.getElementById('selectForm').submit();
}

function progressStudents(){
    var checkedCheckboxs = document.querySelectorAll('input[name=idStudent]:checked')
    if(checkedCheckboxs.length == 0){
        alert("Выберите студента!!!");
        return;
    }

    if(checkedCheckboxs.length > 1){
        alert("Выберите только одного студента!!!");
        return;
    }
    // 1 - string
    var id = checkedCheckboxs[0].value;
    document.getElementById("progressHidden").value = id;
    document.getElementById('progressForm').submit();

}

function selectTerm(){
    var idTerm = document.getElementById("selectDisc").value;
    document.getElementById("disciplinesHidden").value = idTerm;

    var nameGroup = document.getElementById("group").value;
    document.getElementById("termHidden").value = nameGroup;
    document.getElementById('termForm').submit();
}

function selectGroup(){
    var nameGroup = document.getElementById("group").value;
    document.getElementById("termHidden").value = nameGroup;
    document.getElementById('termForm').submit();
}

function selectTermForModify() {
    var idTerm = document.getElementById("selectDisc").value;

    document.getElementById("termModifyHidden").value = idTerm;
    document.getElementById('modifyTermForm').submit();
}

function selectYears(){
    var idTerm = document.getElementById("selectDisc").value;
    document.getElementById("disciplinesHidden").value = idTerm;

    var nameGroup = document.getElementById("group").value;
    document.getElementById("termHidden").value = nameGroup;

    var idYear = document.getElementById("years").value;
    document.getElementById("yearsHidden").value = idYear;
    document.getElementById('termForm').submit();
}

function selectFirstWeek(){
    var idTerm = document.getElementById("selectDisc").value;
    document.getElementById("disciplinesHidden").value = idTerm;

    var nameGroup = document.getElementById("group").value;
    document.getElementById("termHidden").value = nameGroup;

    var idYear = document.getElementById("years").value;
    document.getElementById("yearsHidden").value = idYear;

    var firstWeek = document.getElementById("firstWeek").value;
    document.getElementById("firstWeekHidden").value = firstWeek;
    document.getElementById('termForm').submit();
}
