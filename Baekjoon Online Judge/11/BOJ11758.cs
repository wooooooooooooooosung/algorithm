namespace algorithm
{

    /// <summary>
    /// CCW 알고리즘
    /// </summary>
    
    static class BOJ11758
    {
        static void Main(string[] args)
        {
            int[] x = new int[3];
            int[] y = new int[3];

            for (int i = 0; i < 3; i++)
            {
                string[] input = Console.ReadLine().Split();
                x[i] = int.Parse(input[0]);
                y[i] = int.Parse(input[1]);
            }

            int value = ((x[1] - x[0]) * (y[2] - y[0])) - ((x[2] - x[0]) * (y[1] - y[0]));
            
            if (value < 0) Console.WriteLine(-1); // 반시계방향
            else if (value > 0) Console.WriteLine(1); // 시계방향
            else if (value == 0) Console.WriteLine(0); // 일직선
        }
    }

}
