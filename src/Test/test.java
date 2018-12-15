package Test;

public class test {
    public static void main(String[] args) {
        Server server = new Server();
        server.greet(new Customer(false, true));
    }
}
class Customer {

        Boolean likesSeat;

        Boolean happy;


        public Customer(boolean chilled, boolean goodmood){

            likesSeat = chilled;

            happy = goodmood;

        }


        public void beSeated() throws HateSeatException {

            if (!likesSeat){

                throw new HateSeatException();

            }

        }


        public void beServed() throws TotallyUnhappyException {

            if (!happy){

                throw new TotallyUnhappyException();

            }

        }

    }
    class Server {


        public void greet(Customer c){

            try {

                seat(c);

            } catch (TotallyUnhappyException e) {

                System.out.println("Here is a gift card ... goodbye!");

            }

            finally {

                System.out.println("Time to go home for the day!");

            }

        }



        public void seat(Customer c) throws TotallyUnhappyException {

            try {

                c.beSeated();

                System.out.println("Lovely -- they were seated");

            } catch (HateSeatException e) {

                System.out.println("Oh dear! we will reseat you!");

            }


            serve(c);

        }



        public void serve(Customer c) throws TotallyUnhappyException {

            c.beServed();

            System.out.println("Phew -- happy customer");

        }

    }

