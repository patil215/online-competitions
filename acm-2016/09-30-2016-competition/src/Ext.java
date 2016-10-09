import java.util.*;

/**
 * Created by patil215 on 9/30/16.
 */
public class Ext {

    public static class Extent {
        public Extent(int startInd, int endInd) {
            this.startInd = startInd;
            this.endInd = endInd;
        }

        int startInd;
        int endInd;
    }

    public static class File {
        String name;
        char type;
        int numExtents;
        Extent[] extents;

        public File(String name, char type, int numExtents, Extent[] extents) {
            this.name = name;
            this.type = type;
            this.numExtents = numExtents;
            this.extents = extents;
        }

        public String toString() {
            return name + " " + type + " " + numExtents + " " + genExtentString();
        }

        public String genExtentString() {
            String extentString = "";
            for (int i = 0; i < extents.length; i++) {
                extentString += (extents[i].startInd + 1) + "-" + (extents[i].endInd + 1) + " ";
            }
            return extentString.substring(0, extentString.length() - 1);
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();

        for (int i = 0; i < cases; i++) {
            execute(scanner, i);
        }
    }

    public static void execute(Scanner scanner, int q) {
        int numBlocks = scanner.nextInt();
        int numFiles = scanner.nextInt();

        boolean[] memory = new boolean[numBlocks];

        ArrayList<File> files = new ArrayList<>();

        for (int i = 0; i < numFiles; i++) {

            String name = scanner.next();
            char type = scanner.next().charAt(0);
            int numExtents = scanner.nextInt();
            Extent[] extents = new Extent[numExtents];

            for (int d = 0; d < numExtents; d++) {
                String[] range = scanner.next().split("-");

                extents[d] = new Extent(Integer.parseInt(range[0]) - 1, Integer.parseInt(range[1]) - 1);

                for (int k = extents[d].startInd; k <= extents[d].endInd; k++) {
                    memory[k] = true;
                }
            }

            Arrays.sort(extents, (o1, o2) -> {
                if (o1.startInd > o2.startInd) {
                    return 1;
                } else if (o1.startInd < o2.startInd) {
                    return -1;
                }
                return 0;
            });

            files.add(new File(name, type, numExtents, extents));
        }

        int numPasses = scanner.nextInt();

        for (int p = 0; p < numPasses; p++) {

            Collections.sort(files, (o1, o2) -> {
                if (o1.extents[0].startInd > o2.extents[0].startInd) {
                    return 1;
                } else if (o1.extents[0].startInd < o2.extents[0].startInd) {
                    return -1;
                }
                return 0;
            });

            for (File file : files) {
                if (file.type == 'I') {
                    continue;
                }
                int endInd = memory.length;
                int currentInd = memory.length - 1;

                int sumBlocks = computeSumBlocks(file);


                ArrayList<Extent> extents = new ArrayList<>();
                for(File f : files) {
                    for(int v = 0; v < f.extents.length; v++) {
                        extents.add(f.extents[v]);
                    }
                }

                Collections.sort(extents, (o1, o2) -> {
                    if(o1.endInd > o2.endInd) {
                        return -1;
                    } else if(o1.endInd < o2.endInd) {
                        return 1;
                    } else {
                        return 0;
                    }
                });

                boolean worked = true;
                /*for(int i = 0; i < extents.size(); i++) {
                    currentInd = extents.get(i).endInd;
                    boolean worky = true;
                    for(int d = currentInd; d < sumBlocks; d++) {
                        if(memory[sumBlocks] || d >= memory.length) {
                            worky = false;
                        }
                    }
                    if(worky) {
                        worked = true;
                        break;
                    }
                }*/

                while ((endInd - currentInd) < sumBlocks) {
                    currentInd--;
                    if (currentInd < 0) {
                        worked = false;
                        break;
                    }
                    if (memory[currentInd]) {
                        endInd = currentInd;
                    }
                }

                if (!worked) {
                    continue;
                }

                // We found the right spot to put it!
                for (int i = 0; i < file.extents.length; i++) {
                    for (int d = file.extents[i].startInd; d <= file.extents[i].endInd; d++) {
                        memory[d] = false;
                    }
                }

                for (int i = currentInd; i <= (currentInd + sumBlocks - 1); i++) {
                    memory[i] = true;
                }

                file.extents = new Extent[]{new Extent(currentInd, currentInd + sumBlocks - 1)};
            }

            Collections.sort(files, (o1, o2) -> {
                if (o1.extents[o1.extents.length - 1].endInd > o2.extents[o2.extents.length - 1].endInd) {
                    return -1;
                } else if (o1.extents[o1.extents.length - 1].endInd < o2.extents[o2.extents.length - 1].endInd) {
                    return 1;
                }
                return 0;
            });

            for (File file : files) {
                if (file.type == 'I') {
                    continue;
                }
                int startInd = -1;
                int currentInd = 0;

                int sumBlocks = computeSumBlocks(file);

                boolean worked = true;
                while ((currentInd - startInd) < sumBlocks) {
                    currentInd++;
                    if (currentInd >= memory.length) {
                        worked = false;
                        break;
                    }
                    if (memory[currentInd]) {
                        startInd = currentInd;
                    }
                }

                if (!worked) {
                    continue;
                }

                // We found the right spot to put it!
                for (int i = 0; i < file.extents.length; i++) {
                    for (int d = file.extents[i].startInd; d <= file.extents[i].endInd; d++) {
                        memory[d] = false;
                    }
                }

                for (int i = currentInd - sumBlocks + 1; i <= currentInd; i++) {
                    memory[i] = true;
                }

                file.extents = new Extent[]{new Extent(currentInd - sumBlocks + 1, currentInd)};
            }

        }

        Collections.sort(files, (o1, o2) -> {
            if (o1.extents[0].startInd > o2.extents[0].startInd) {
                return 1;
            } else if (o1.extents[0].startInd < o2.extents[0].startInd) {
                return -1;
            }
            return 0;
        });

        System.out.println("DATA SET #" + (q + 1));
        for (File file : files) {
            System.out.println(file);
        }
    }

    private static int computeSumBlocks(File file) {
        int sumBlocks = 0;
        for (int i = 0; i < file.extents.length; i++) {
            sumBlocks += ((file.extents[i].endInd - file.extents[i].startInd));
        }
        sumBlocks += 1;
        return sumBlocks;
    }


}
