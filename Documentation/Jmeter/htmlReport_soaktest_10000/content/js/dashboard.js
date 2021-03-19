/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 99.66688532070845, "KoPercent": 0.3331146792915609};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.8652732034443078, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.18916761687571265, 500, 1500, "Album Controller"], "isController": true}, {"data": [1.0, 500, 1500, "Create User (signup)"], "isController": false}, {"data": [0.9620918017812286, 500, 1500, "Update Track"], "isController": false}, {"data": [0.12736386420596946, 500, 1500, "Login User"], "isController": false}, {"data": [1.0, 500, 1500, "Read All Genres"], "isController": false}, {"data": [0.14383561643835616, 500, 1500, "Playlist Controller"], "isController": true}, {"data": [1.0, 500, 1500, "Delete Playlist"], "isController": false}, {"data": [1.0, 500, 1500, "create album for track"], "isController": false}, {"data": [1.0, 500, 1500, "Create Playlist"], "isController": false}, {"data": [1.0, 500, 1500, "Variables"], "isController": false}, {"data": [1.0, 500, 1500, "create user for playlist"], "isController": false}, {"data": [0.12554112554112554, 500, 1500, "Read All Users"], "isController": false}, {"data": [1.0, 500, 1500, "Create Genre"], "isController": false}, {"data": [0.20672748004561003, 500, 1500, "Read All Albums"], "isController": false}, {"data": [0.9892816419612315, 500, 1500, "Update Genre"], "isController": false}, {"data": [1.0, 500, 1500, "Create Artist"], "isController": false}, {"data": [0.9904218928164196, 500, 1500, "Update artist"], "isController": false}, {"data": [1.0, 500, 1500, "create playlist for track"], "isController": false}, {"data": [1.0, 500, 1500, "Logout User"], "isController": false}, {"data": [1.0, 500, 1500, "Delete Album"], "isController": false}, {"data": [1.0, 500, 1500, "Read By Username"], "isController": false}, {"data": [1.0, 500, 1500, "Read By Id"], "isController": false}, {"data": [1.0, 500, 1500, "Update Playlist"], "isController": false}, {"data": [1.0, 500, 1500, "Delete Track"], "isController": false}, {"data": [0.9614595210946408, 500, 1500, "Update user"], "isController": false}, {"data": [1.0, 500, 1500, "Delete User"], "isController": false}, {"data": [1.0, 500, 1500, "Create Track"], "isController": false}, {"data": [0.9904218928164196, 500, 1500, "Artist Controller"], "isController": true}, {"data": [1.0, 500, 1500, "Read All Tracks"], "isController": false}, {"data": [1.0, 500, 1500, "Read All Arists"], "isController": false}, {"data": [0.14977168949771688, 500, 1500, "Read All Playlists"], "isController": false}, {"data": [1.0, 500, 1500, "Create Album"], "isController": false}, {"data": [1.0, 500, 1500, "Delete Genre"], "isController": false}, {"data": [0.9617492578214204, 500, 1500, "Track Controller"], "isController": true}, {"data": [0.05570745044429255, 500, 1500, "User Controller"], "isController": true}, {"data": [0.9890535917901938, 500, 1500, "Genre Controller"], "isController": true}, {"data": [0.9634703196347032, 500, 1500, "Update Album"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 175315, 584, 0.3331146792915609, 376.7666086758046, 0, 23617, 5.0, 4038.4000000000087, 7725.850000000002, 10662.970000000005, 24.326161915399933, 577.5526705658071, 4.740247939026759], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["Album Controller", 4385, 160, 3.6488027366020526, 2595.2964652223486, 61, 10361, 2257.0, 4931.0, 5876.7, 8059.400000000003, 0.6086491472874027, 230.81252865404977, 0.6870111797379796], "isController": true}, {"data": ["Create User (signup)", 4389, 0, 0.0, 14.062200956937787, 1, 350, 10.0, 32.0, 39.0, 65.10000000000036, 0.6110050232158243, 0.20160271662313714, 0.14439767150217722], "isController": false}, {"data": ["Update Track", 4379, 166, 3.790819821877141, 12.474994290933951, 1, 167, 5.0, 31.0, 38.0, 63.0, 0.6097396262807108, 0.49514516122210533, 0.198580628988754], "isController": false}, {"data": ["Login User", 4389, 0, 0.0, 4250.733424470269, 18, 14911, 3741.0, 8108.0, 8885.0, 10785.500000000002, 0.6102315348303309, 0.15553752987374644, 0.13408407747736764], "isController": false}, {"data": ["Read All Genres", 4385, 0, 0.0, 7.985404789053575, 0, 187, 2.0, 25.0, 35.0, 64.0, 0.6092841002138261, 0.2311066111180662, 0.10829072874894174], "isController": false}, {"data": ["Playlist Controller", 4380, 0, 0.0, 3651.2463470319653, 61, 14820, 3135.0, 7076.8, 8027.549999999998, 11328.799999999992, 0.6078773409002497, 123.55998686675281, 0.8490148136793497], "isController": true}, {"data": ["Delete Playlist", 4379, 0, 0.0, 13.145695364238376, 1, 371, 5.0, 32.0, 39.0, 65.0, 0.6097294383053783, 0.11372883077766333, 0.11484369800277755], "isController": false}, {"data": ["create album for track", 4379, 0, 0.0, 8.152089518154824, 1, 209, 2.0, 25.0, 35.0, 61.0, 0.6097323248638232, 0.24167509649337773, 0.17386898326194958], "isController": false}, {"data": ["Create Playlist", 4380, 0, 0.0, 8.689954337899511, 1, 249, 2.0, 27.0, 36.0, 60.0, 0.6086471247398659, 0.21211850058148035, 0.1788352822055621], "isController": false}, {"data": ["Variables", 21908, 0, 0.0, 0.0495252875661861, 0, 5, 0.0, 0.0, 0.0, 1.0, 3.044027097232267, 2.02972666972174, 0.0], "isController": false}, {"data": ["create user for playlist", 4380, 0, 0.0, 8.88767123287669, 1, 227, 3.0, 23.90000000000009, 31.0, 53.1899999999996, 0.608647463051764, 0.19963876169457737, 0.1426517491527572], "isController": false}, {"data": ["Read All Users", 4389, 0, 0.0, 4301.685577580311, 9, 16849, 3713.0, 8257.0, 9347.0, 12206.400000000122, 0.6090553162105852, 216.21391373378975, 0.10765528538487883], "isController": false}, {"data": ["Create Genre", 4385, 0, 0.0, 13.984036488027364, 0, 369, 10.0, 32.0, 42.0, 75.28000000000065, 0.6092835076055534, 0.1902512936679812, 0.14399082894584367], "isController": false}, {"data": ["Read All Albums", 4385, 0, 0.0, 2545.479133409352, 14, 10273, 2218.0, 4873.8, 5801.0, 7957.260000000003, 0.6086670580088162, 229.27586930100765, 0.10818105913828568], "isController": false}, {"data": ["Update Genre", 4385, 47, 1.071835803876853, 12.271379703534775, 1, 351, 4.0, 33.0, 41.0, 71.0, 0.6092852007751053, 0.2776829677552655, 0.15157649223984634], "isController": false}, {"data": ["Create Artist", 4385, 0, 0.0, 9.15826681870015, 0, 197, 3.0, 28.0, 36.0, 60.0, 0.609285370092578, 0.17418673364301518, 0.12673611702121007], "isController": false}, {"data": ["Update artist", 4385, 42, 0.9578107183580388, 11.763740022805, 1, 227, 4.0, 30.0, 40.0, 68.0, 0.6092882485040132, 0.2510753522912295, 0.13253699268069044], "isController": false}, {"data": ["create playlist for track", 4379, 0, 0.0, 10.28842201415853, 1, 279, 4.0, 25.0, 31.0, 54.0, 0.6097313060753689, 0.21249782503002013, 0.17915382411110672], "isController": false}, {"data": ["Logout User", 4385, 0, 0.0, 1.3179019384264554, 0, 43, 1.0, 2.0, 3.0, 6.0, 0.6092824070503909, 0.16243564172339522, 0.11959547247766462], "isController": false}, {"data": ["Delete Album", 4380, 0, 0.0, 16.766438356164365, 1, 267, 14.0, 36.0, 46.0, 83.1899999999996, 0.6086456023409789, 0.11352666996789743, 0.11285615994337929], "isController": false}, {"data": ["Read By Username", 4385, 0, 0.0, 15.59657924743444, 1, 243, 11.0, 32.0, 42.0, 81.0, 0.6092736874077233, 0.1998414003971603, 0.12137874241325736], "isController": false}, {"data": ["Read By Id", 21908, 0, 0.0, 10.998950155194471, 0, 271, 5.0, 27.0, 35.0, 60.0, 3.0440486680967083, 1.0308726907806927, 0.5576713857462574], "isController": false}, {"data": ["Update Playlist", 4379, 0, 0.0, 6.198903859328619, 1, 257, 3.0, 13.0, 25.0, 44.0, 0.6097293534070142, 0.22261810873727264, 0.19158145505177965], "isController": false}, {"data": ["Delete Track", 4379, 0, 0.0, 12.154373144553553, 1, 343, 4.0, 31.0, 38.0, 67.0, 0.6097413243097001, 0.11373104779604758, 0.11298506891754832], "isController": false}, {"data": ["Update user", 4385, 169, 3.854047890535918, 7.5096921322690875, 1, 220, 3.0, 19.0, 30.0, 57.14000000000033, 0.6092763117399417, 0.5016252484694104, 0.16473747368370958], "isController": false}, {"data": ["Delete User", 8770, 0, 0.0, 13.67514253135686, 1, 217, 5.0, 34.0, 45.0, 74.28999999999905, 1.2185468668825776, 0.22728755036579332, 0.23717611311720546], "isController": false}, {"data": ["Create Track", 4379, 0, 0.0, 8.300753596711571, 1, 169, 3.0, 23.0, 31.0, 55.19999999999982, 0.6097325795614688, 0.19634532462759874, 0.1897972306301995], "isController": false}, {"data": ["Artist Controller", 4385, 42, 0.9578107183580388, 50.326795895096936, 9, 347, 43.0, 85.0, 110.0, 174.42000000000098, 0.6092836769220851, 1.3158161790365148, 0.5933641760615848], "isController": true}, {"data": ["Read All Tracks", 4379, 0, 0.0, 10.31902260790137, 1, 192, 4.0, 27.0, 34.0, 54.19999999999982, 0.6097372490560142, 0.20277463213517202, 0.10837126887519002], "isController": false}, {"data": ["Read All Arists", 4385, 0, 0.0, 8.251539338654515, 1, 163, 2.0, 24.0, 34.0, 58.14000000000033, 0.6092874865689277, 0.1966090053263676, 0.10888633793175174], "isController": false}, {"data": ["Read All Playlists", 4380, 0, 0.0, 3599.4162100456615, 14, 14773, 3091.0, 7013.5, 7989.849999999999, 11269.509999999987, 0.6078814747704206, 122.19837309713495, 0.10982233675051545], "isController": false}, {"data": ["Create Album", 4385, 0, 0.0, 10.119954389965791, 1, 222, 3.0, 28.0, 37.0, 64.14000000000033, 0.6092901956801118, 0.24149791914719101, 0.17374290736190684], "isController": false}, {"data": ["Delete Genre", 4385, 0, 0.0, 14.427137970353465, 1, 238, 8.0, 34.0, 43.0, 77.14000000000033, 0.6092842695306872, 0.11364579636754028, 0.11290098955199057], "isController": false}, {"data": ["Track Controller", 4379, 166, 3.790819821877141, 70.61520895181555, 16, 682, 61.0, 110.0, 145.0, 229.19999999999982, 0.6097287591191278, 2.0653783661354366, 1.0739430997595756], "isController": true}, {"data": ["User Controller", 4389, 169, 3.850535429482798, 8605.953748006377, 77, 28732, 7352.0, 16612.0, 17860.0, 21188.200000000004, 0.6089960750348656, 217.9306238420888, 0.9140483198805651], "isController": true}, {"data": ["Genre Controller", 4385, 47, 1.071835803876853, 55.68711516533644, 12, 744, 48.0, 92.0, 124.0, 211.0, 0.6092803752666904, 1.0029316678946063, 0.6278709819279242], "isController": true}, {"data": ["Update Album", 4380, 160, 3.65296803652968, 7.472831050228293, 1, 136, 3.0, 19.0, 30.0, 54.0, 0.6086525377753667, 0.5245676413074413, 0.18180636621893648], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["500", 584, 100.0, 0.3331146792915609], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 175315, 584, "500", 584, null, null, null, null, null, null, null, null], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["Update Track", 4379, 166, "500", 166, null, null, null, null, null, null, null, null], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["Update Genre", 4385, 47, "500", 47, null, null, null, null, null, null, null, null], "isController": false}, {"data": [], "isController": false}, {"data": ["Update artist", 4385, 42, "500", 42, null, null, null, null, null, null, null, null], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["Update user", 4385, 169, "500", 169, null, null, null, null, null, null, null, null], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["Update Album", 4380, 160, "500", 160, null, null, null, null, null, null, null, null], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
