package server;
import client.Map;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class Path {

    private Point target;
    private Map map;
    private Integer[][] path;

    public Path(Point target, Map map){

        this.target = target;
        this.map = map;
        path = new Integer[map.getTileX()][map.getTileY()];
        generatePath();
    }

    public void generatePath(){
        int[][] offsets = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }};

        Queue<Point> queue = new LinkedList<Point>();
        LinkedList<Point> visited = new LinkedList<Point>();
        queue.add(target);
        visited.add(target);
        while(queue.size() > 0){
            Point current = queue.poll();
            for(int i = 0; i < offsets.length; i++)
            {
                Point newPoint = new Point(current.x + offsets[i][0], current.y + offsets[i][1]);
                if(visited.contains(newPoint))
                    continue;
                if(newPoint.x < 0 || newPoint.y < 0 || newPoint.x >= map.getTileX() || newPoint.y >=map.getTileY())
                    continue;
                if(path[current.x][current.y] == null){
                    path[current.x][current.y] = 0;
                }
                if(map.getLevel()[newPoint.y][newPoint.x] != null){
                        path[newPoint.x][newPoint.y] = 999;
                    continue;
                }
                path[newPoint.x][newPoint.y] = (path[current.x][current.y] + 1);
                queue.add(newPoint);
                visited.add(newPoint);
            }
        }

        for(int i = 0; i < map.getTileX(); i++){
            for(int j = 0; j < map.getTileY(); j++){
                if(path[i][j] != null)
                    if(path[i][j] == -1)
                        path[i][j] = 0;
            }
        }


    }

    public Integer[][] getPath(){
        return path;
    }

    public void paintPath(Graphics2D g2d) {
        Font font = new Font("SansSerrif",Font.PLAIN, 7);
        g2d.setFont(font);
        for (int y = 0; y < map.getTileY(); y++) {
            for (int x = 0; x < map.getTileX(); x++) {
                g2d.drawString("" + path[x][y], x * 32 + 8, y * 32 + 16);
            }
        }
    }
}