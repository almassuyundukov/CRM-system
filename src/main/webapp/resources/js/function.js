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

function deleteDisciplines(){
    var checkedCheckboxs = document.querySelectorAll('input[name=disciplineId]:checked')
    if(checkedCheckboxs.length == 0){
        alert("Выберите хотя бы одну дисциплину!!!");
        return;
    }

    // 1-2-5-7 - string
    var ids = ""
    for(var i = 0; i < checkedCheckboxs.length; i++){
        ids = ids + checkedCheckboxs[i].value + " ";
    }

    document.getElementById("discDeleteHidden").value = ids;
    document.getElementById('discDeleteForm').submit();

}

function modifyDiscipline(){
    var checkedCheckboxs = document.querySelectorAll('input[name=disciplineId]:checked')
    if(checkedCheckboxs.length == 0){
        alert("Выберите дисциплину!!!");
        return;
    }

    if(checkedCheckboxs.length > 1){
        alert("Выберите только одну дисциплину!!!");
        return;
    }
    // 1 - string
    var id = checkedCheckboxs[0].value;
    document.getElementById("discModifyHidden").value = id;
    document.getElementById('discModifyForm').submit();

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

function progressToDayStudents(){
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
    document.getElementById("progressToDayHidden").value = id;
    document.getElementById('progressToDayForm').submit();

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

function createTimetable(){
    document.getElementById("allParamsWithoutDisciplinesForm").submit();
}

function selectTermOfTimetable(){
    var nameGroup = document.getElementById("group").value;
    document.getElementById("termHidden").value = nameGroup;

    var idSelectedTerm = document.getElementById("selectTerm").value;
    document.getElementById("selectTermHidden").value = idSelectedTerm;
    document.getElementById('termForm').submit();
}

function selectDiscForTeacher(){
    var idDisc = document.getElementById("selectDisc").value;
    document.getElementById("disciplineHidden").value = idDisc;

    var nameGroup = document.getElementById("group").value;
    document.getElementById("groupHidden").value = nameGroup;

    var idTerm = document.getElementById("selectTerm").value;
    document.getElementById("termHidden").value = idTerm;
    document.getElementById("progressForTeacherForm").submit();
}

function selectTimetableByTerm(){
    var idDisc = document.getElementById("selectDisc").value;
    document.getElementById("disciplineHidden").value = idDisc;

    var nameGroup = document.getElementById("group").value;
    document.getElementById("groupHidden").value = nameGroup;

    var idTerm = document.getElementById("selectTerm").value;
    document.getElementById("termHidden").value = idTerm;

    document.getElementById("downloadTimetableHidden").value = "1";
    document.getElementById("progressForTeacherForm").submit();
}

function downloadStPerformanceForDB(){
    document.getElementById("studentsPerformance").submit();
}

function getStudentPerfForTerm(){
    var idTerm = document.getElementById("selectTerm").value;
    document.getElementById("termHidden").value = idTerm;

    document.getElementById("downloadTimetableForStudentHidden").value = "1";
    document.getElementById("progressForStudentForm").submit();
}

function modifyTimetable(){
    document.getElementById("termModifyForm").submit();
}

function deleteTimetable(){
    document.getElementById("timetableDeleteForm").submit();
}

function createStudent(){
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
    document.getElementById("studentIdHidden").value = id;
    document.getElementById('createStudentForm').submit();

}
