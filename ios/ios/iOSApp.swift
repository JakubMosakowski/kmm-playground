import SwiftUI
import shared

@main
struct iOSApp: App {
	let database = Database(databaseDriverFactory: DatabaseDriverFactory())
	var body: some Scene {
		WindowGroup {
			ContentView(viewModel: .init(database: database))
		}
	}
}
