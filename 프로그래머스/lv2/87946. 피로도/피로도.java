import java.util.*;

class Solution {
    static int answer;
    public int solution(int k, int[][] dungeons) {
        
        ArrayList<int[]> can = new ArrayList<>();
        solut(can, dungeons, k);
        return answer;
    }
    
    public static int solut(ArrayList<int[]> can, int[][] dungeons, int hp){
        // answer = Math.max(can.size(), answer);
        for (int i = 0; i < dungeons.length; i++) {
            if (dupli(can, dungeons[i]) != -1){
                continue;
            }
            int need = dungeons[i][0];
            int real = dungeons[i][1];
            
            if (hp >= need){
                can.add(dungeons[i]);
                answer = Math.max(solut(can, dungeons, hp - real), answer);
                // solut(can, dungeons, hp - real);
                can.remove(dupli(can, dungeons[i]));
            }
        }
        return can.size();
    }
    
    public static int dupli(ArrayList<int[]> can, int[] d){
        for (int i = 0; i < can.size(); i++){
            if (Arrays.equals(can.get(i), d)){
                return i;
            }
        }
        return -1;
    }
}