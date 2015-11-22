<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Knowledge Representation</title>
<script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">
    var dataset = null
   	d3.json("<s:property value ="uri"/>", function(err, graph)
    {
      dataset = graph 
      
   })

</script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="/asset/jquery-1.11.2.js"></script>
<script src="/asset/jquery-migrate-1.2.1.js"></script>
</head>
<body style="background-color:#FFF1BA ">
<nav class = "navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Knowledge Representation</a>
    </div>
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">View Graph <span class="sr-only">(current)</span></a></li>
      </ul>
  </div>
</nav>

<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
            <h4 class="modal-title" id="myModalLabel">xyz</h4>
            </div>
            <form class="form-horizontal">
            <div class="modal-body">
                  <div class = "form-group">
                    <label for = "fname" class = "col-lg-2 control-label">First Name</label>
                    <div class="col-lg-8">
                    <input type = "text" class ="form-control" id = "fname" name = "fname">
                    </div>
                  </div>
                  </div>
                  
                  <div class = "form-group">
                    <label for = "relation" class = "col-lg-2 control-label">Relation</label>
                    <div class="col-lg-8">
                    <select name = "relation", id = "relation" class = "form-control">
                   <option>Schematic is</option>
                      <option>Semantic is</option>
                      <option>Linguistic is</option>
                    </select>
                    </div>
                  </div>
                
                <div class = "form-group">
                    <label for = "relative" class = "col-lg-2 control-label">Relative</label>
                    <div class="col-lg-8">
                    <select name = "relative", id = "relative" class = "form-control">
                    </select>
                    </div>
                  </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </form>
        </div>
    </div>
  </div>
</div>
<script type="text/javascript">
    var x = document.getElementById("relative")
    alert(x.innerHTML);
    for(var i = 0;dataset.nodes[i] != null ; i++)
    {
        var option = document.createElement("option");
        option.text = dataset.nodes[i].name + "(" + dataset.nodes[i].code + ")";
        x.add(option);
    }
    var w = window.innerWidth;
    var h = window.innerHeight - 150;
    var linkDistance=200;
    var circleWidth = 50;
    var colors = d3.scale.category10();

 
    var svg = d3.select("body").append("svg").attr({"width":w,"height":h});

    var tooltip = d3.select("body")
        .append("div")
        .style("z-index", "10")
        .text("Knowledge Representation")
        .style("padding", "20px")
        .style("background", "#4D94FF")
        .style("border-radius", "15px")

    var force = d3.layout.force()
        .nodes(dataset.nodes)
        .links(dataset.edges)
        .size([w,h])
        .linkDistance([linkDistance])
        .charge(-500)
        .theta(0.1)
        .gravity(0.055)
        .start();

 

    var edges = svg.selectAll("line")
      .data(dataset.edges)
      .enter()
      .append("line")
      .attr("id",function(d,i) {return 'edge'+i})
      .attr('marker-end','url(#arrowhead)')
      .style("stroke","#000")
      .style("pointer-events", "none");
    
    var nodes = svg.selectAll("circle")
      .data(dataset.nodes)
      .enter()
      .append("circle")
      .attr({"r":25})
      .style("fill",function(d,i){
        return colors(2);
      })
      .call(force.drag)
      .on("mouseover", function(d){
        return tooltip
        .text("Name: " + d.name);
      })
      .on("mouseout", function(){
        return tooltip
        .text("Knowledge Representation")
      })

    var nodelabels = svg.selectAll(".nodelabel") 
       .data(dataset.nodes)
       .enter()
       .append("text")
       .attr({"x":   function(d, i) { return circleWidth + 30; },
              "y":   function(d, i) { if (i>0) { return circleWidth + 10 }    else { return 38 } },
              "class":"nodelabel",
              "stroke":"#003D4D"})
       .attr("font-family",  "Bree Serif")
       .text(function(d){return d.name;})
       .attr("text-anchor",  function(d, i) { if (i>0) { return  "beginning"; }      else { return "end" } });

    var edgepaths = svg.selectAll(".edgepath")
        .data(dataset.edges)
        .enter()
        .append('path')
        .attr({'d': function(d) {return 'M '+d.source.x+' '+d.source.y+' L '+ d.target.x +' '+d.target.y},
               'class':'edgepath',
               'fill-opacity':0,
               'stroke-opacity':0,
               'id':function(d,i) {return 'edgepath'+i}})
        .style("pointer-events", "none");

    var edgelabels = svg.selectAll(".edgelabel")
        .data(dataset.edges)
        .enter()
        .append('text')
        .style("pointer-events", "none")
        .attr({'class':'edgelabel',
               'id':function(d,i){return 'edgelabel'+i},
               'dx':80,
               'dy':0,
               'font-size':10,
               'fill':'#003D99'});

    edgelabels.append('textPath')
        .attr('xlink:href',function(d,i) {return '#edgepath'+i})
        .style("pointer-events", "none")
        .text(function(d,i){return d.val})
        .attr("text-anchor",  function() {  return "middle";})


    svg.append('defs').append('marker')
        .attr({'id':'arrowhead',
               'viewBox':'-0 -5 10 10',
               'refX':25,
               'refY':0,
               //'markerUnits':'strokeWidth',
               'orient':'auto',
               'markerWidth':10,
               'markerHeight':10,
               'xoverflow':'visible'})
        .append('svg:path')
            .attr('d', 'M 0,-5 L 10 ,0 L 0,5')
            .attr('fill', '#ccc')
            .attr('stroke','#ccc');
     

    force.on("tick", function(){

        edges.attr({"x1": function(d){return d.source.x;},
                    "y1": function(d){return d.source.y;},
                    "x2": function(d){return d.target.x;},
                    "y2": function(d){return d.target.y;}
        });

        nodes.attr({"cx":function(d){return d.x;},
                    "cy":function(d){return d.y;}
        });

        nodelabels.attr("x", function(d) { return d.x; }) 
                  .attr("y", function(d) { return d.y; });

        edgepaths.attr('d', function(d) { var path='M '+d.source.x+' '+d.source.y+' L '+ d.target.x +' '+d.target.y;return path});       

        edgelabels.attr('transform',function(d,i){
            if (d.target.x<d.source.x){
                bbox = this.getBBox();
                rx = bbox.x+bbox.width/2;
                ry = bbox.y+bbox.height/2;
                return 'rotate(180 '+rx+' '+ry+')';
                }
            else 
            {
               return 'rotate(0)';
            }
        });
    });

</script>
<div id = "test">
</div>
</body>
</html>
