import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        NavigationView {
            listView()
                    .navigationTitle("Users")
                    .navigationBarItems(
                            leading:
                            Button("Add") {
                                self.viewModel.addUser()
                            }
                            ,
                            trailing:
                            Button("Reload") {
                                self.viewModel.loadUsers()
                            }
                    )
        }
                .navigationViewStyle(.stack)
    }

    private func listView() -> AnyView {
        return AnyView(List(viewModel.users) { user in
            Text(user.full_name)
        })
    }
}

extension ContentView {

    class ViewModel: ObservableObject {
        let database: Database
        @Published var users: [User] = []

        init(database: Database) {
            self.database = database
            loadUsers()
        }

        func loadUsers() {
            database.getAllUsers { users, error in
                if let users = users {
                    self.users = users
                } else {

                }
            }
        }

        func addUser() {
            database.insertUser(fullName: "Next user")
            loadUsers()
        }
    }
}

extension User: Identifiable {
}
